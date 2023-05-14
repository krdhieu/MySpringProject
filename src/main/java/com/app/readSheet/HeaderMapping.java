package com.app.readSheet;


import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import java.util.LinkedHashMap;
import java.util.Map;

public class HeaderMapping {
    private Map<String, Integer> headerMap = new LinkedHashMap<>();

    /* store the index of the column corresponding to the field name of the entity.
    eg:  key: "name" value: 1
         key: "age" value: 2
         name, age are field's names - headers
         1,2 are index of colum
     */
    public void addHeader(Row row) {
        String header = "";
        for (int i = 0; i < row.getLastCellNum(); i++) {
            if (row.getCell(i).getCellType() == CellType.NUMERIC) {
                header = String.valueOf(row.getCell(i).getNumericCellValue());
            } else if (row.getCell(i).getCellType() == CellType.BOOLEAN) {
                header = String.valueOf(row.getCell(i).getBooleanCellValue());
            } else header = row.getCell(i).getStringCellValue();
            if (row.getCell(i).getCellType() == CellType.BLANK) continue;
            headerMap.put(header, i);
        }
    }

        public Map<String, Integer> getHeaderMap() {
        return headerMap;
    }

    public void setHeaderMap(Map<String, Integer> headerMap) {
        this.headerMap = headerMap;
    }
}
