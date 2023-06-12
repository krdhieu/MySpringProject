package com.app.readSheet;

import java.io.File;
import java.io.FileInputStream;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import java.util.Map;

import com.app.logic.common.EntityLogic;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContext;


@Component
public class ReadSheet<T, I> {
    @Autowired
    ApplicationContext context;
    static XSSFRow row;
    EntityLogic entityLogic;

    @Autowired
    ServletContext servletContext;

    public void readRecord(String filePath, Class clazz, int headerRowNum) {
        try {
            String absoluteDiskPath = servletContext.getRealPath(filePath);
            File fl = new File(absoluteDiskPath);
            FileInputStream file = new FileInputStream(fl);
            Workbook workbook = new XSSFWorkbook(file);
            Sheet sheet = workbook.getSheetAt(0);
            Row headerRow = sheet.getRow(0);
            HeaderMapping headerMapping = new HeaderMapping();
            headerMapping.addHeader(headerRow);
            Field[] fields = clazz.getDeclaredFields();
            Constructor<?> constructor = clazz.getDeclaredConstructor();
            for (int i = headerRowNum; i <= sheet.getLastRowNum(); i++) {
                row = (XSSFRow) sheet.getRow(i);
                Object obj = constructor.newInstance();
                for (Map.Entry<String, Integer> entry : headerMapping.getHeaderMap().entrySet()) {
                    for (Field f : fields) {
                        String key = entry.getKey();
                        if (key.equals(f.getName())) {
                            Method withMethod = this.getWithMethod(clazz, f);
                            if (f.getType().isPrimitive() || f.getType() == String.class) {
                                System.out.println("field: " + f.getType() + " is " + f.getType());
                                withMethod.invoke(obj, this.getCellValue(row.getCell(entry.getValue()), f));
                            } else {
                                Object refInstance = this.getRefInstance(f, entry);
                                withMethod.invoke(obj, refInstance);
                            }
                        }
                    }
                }
                entityLogic.saveEntity((T) obj);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Method getWithMethod(Class<?> clazz, Field field) throws NoSuchMethodException {
        return clazz.getDeclaredMethod("with" + Character.toUpperCase(field.getName().charAt(0)) + field.getName().substring(1), field.getType());
    }


    public Object getCellValue(Cell cell, Field field) {
        switch (cell.getCellType()) {
            case BOOLEAN:
                return cell.getBooleanCellValue();
            case NUMERIC:
                if (field.getType() == int.class || field.getType() == Integer.class) {
                    return (int) cell.getNumericCellValue();
                } else if (field.getType() == double.class || field.getType() == Double.class) {
                    return cell.getNumericCellValue();
                } else if (field.getType() == long.class || field.getType() == Long.class) {
                    return (long) cell.getNumericCellValue();
                } else if (field.getType() == float.class || field.getType() == Float.class) {
                    return (float) cell.getNumericCellValue();
                } else return (long) cell.getNumericCellValue(); // if field is entity type return id type - long type
            case STRING:
                return cell.getRichStringCellValue().getString();
            default:
                return null;
        }
    }

    // get reference instance by id mapping from excel of entity to set to field of entity
    public Object getRefInstance(Field f, Map.Entry<String, Integer> entry) throws Exception {
        Class<?> clz = f.getType(); // get type of reference field
        String clzLogicName = "com.app.logic." + clz.getSimpleName() + "Logic";
        Class<?> clzLogic = Class.forName(clzLogicName); // get logic class of reference field
        Method findById = clzLogic.getDeclaredMethod("findById", Long.class); // get findById method from logic class of reference field
        Object clzLogicInstance = context.getBean(clzLogic); // create logic class's bean of reference field to invoke findByIdMethod
        Object refInstance = findById.invoke(clzLogicInstance, this.getCellValue(row.getCell(entry.getValue()), f));
        return refInstance;
    }

    public ReadSheet<T, I> withEntity(EntityLogic<T, I> entityLogic) {
        this.entityLogic = entityLogic;
        return this;
    }
}