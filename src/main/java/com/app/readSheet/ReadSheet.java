package com.app.readSheet;

import java.io.FileInputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;

import com.app.entity.Customer;
import com.app.entity.Role;
import com.app.logic.CustomerLogic;
import com.app.logic.EntityLogic;
import com.app.logic.RoleLogic;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class ReadSheet<T> {
    static XSSFRow row;
    EntityLogic entityLogic;

    public void readRecord(String filePath, Class clazz, int headerRowNum) {
        try {
            FileInputStream file = new FileInputStream(filePath);
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
                            System.out.println();
                            withMethod.invoke(obj, this.getCellValue(row.getCell(entry.getValue()), f));
                        }
                    }
                }
                entityLogic.saveEntity((T) obj);
                System.out.println(obj);
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
                if(field.getType() == int.class || field.getType() == Integer.class) {
                    return (int) cell.getNumericCellValue();
                } else if (field.getType() == double.class || field.getType() == Double.class) {
                    return cell.getNumericCellValue();
                } else if (field.getType() == long.class || field.getType() == Long.class) {
                    return (long) cell.getNumericCellValue();
            }
            case STRING:
                return cell.getStringCellValue();
            default:
                return null;
        }
    }

    public ReadSheet<T> withEntity(EntityLogic<T> entityLogic) {
        this.entityLogic = entityLogic;
        return this;
    }


}
