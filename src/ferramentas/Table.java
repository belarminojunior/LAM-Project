
package ferramentas;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Util class to print fancy tables created by :
 * @author Nuno Fonseca
 */
public class Table {
    
    private char horizontalSeparator;
    private char verticalSeparator;
    private char delimiter;
    private String title;
    
    private ArrayList<String> headers;
    private ArrayList<ArrayList<String>> rows;
    
    int[] sizes;
    
    
    public Table(String title, char horizontalSeparator, char verticalSeparator, char delimiter) {
        this.horizontalSeparator = horizontalSeparator;
        this.verticalSeparator = verticalSeparator;
        this.delimiter = delimiter;
        this.title = title.toUpperCase();
        
        rows = new ArrayList<>();
    }
    
    public Table(String title) {
        this(title, '-', '|', '+');
    }
    
    public Table() {
        this(null, '-', '|', '+');
    }

    // setHeaders
    public void setHeaders(String... headers){
        if (this.headers == null){
            this.headers = new ArrayList<>();
            
            for (String header : headers) {
                this.headers.add(header.toUpperCase());
            }
        }
    }
    // setRows
    public void addRow(String... row){
        ArrayList<String> singleRow = new ArrayList<>();
        singleRow.addAll(Arrays.asList(row));
        
        this.rows.add(singleRow);
    }
    // getTotalColums
    private int getTotalColums(){
        int columns = 0;
        
        if (headers != null)
            columns = Math.max(columns, headers.size());
        
        for (ArrayList<String> row : rows) 
            columns = Math.max(columns, row.size());
        
        return columns;
    }
    
    private void adjustList(ArrayList list){
        int newSize = getTotalColums();
        int toIncrease = newSize - list.size();
        
        if (toIncrease > 0){
            for (int i = 0; i < toIncrease; i++) {
                list.add("");
            }
        }
    }
    
    // getColumnSizes
    private void updateSizes(){
        sizes = new int[getTotalColums()];
        
        for (int i = 0; i < sizes.length; i++) {
            int maxSize = 0;
            
            if (headers != null){
                if (i < headers.size())
                    maxSize = headers.get(i).length();
            }
            for (ArrayList<String> row : rows)
                if (i < row.size())
                    maxSize = Math.max(maxSize, row.get(i).length());
            
            sizes[i] = maxSize;
        }
    }
    
    private String repeat(char character, int times, char sides) {
        String result = "" + character;
        for (int i = 2; i < times; i++) result += character;
        return result + character;
    }
    
    private String repeat(char character, int times) {
        return repeat(character, times, character);
    }
    
    private void printLine() {
        
        String line = delimiter + "";
        
        for (int i = 0; i < sizes.length; i++) {
            line += repeat(horizontalSeparator, sizes[i] + 2) + delimiter;
        }
        System.out.println(line);
    }
    
    // makeRowsFormat
    private String getFormat(){
        String format = verticalSeparator + "";
        for (int i = 0; i < sizes.length; i++) 
            format += " %-" + sizes[i] + "s " + verticalSeparator;
        
        return format + '\n';  
    }
    
    private void printTitle(){
        int totalSize = getTotalColums() * 2 + 1;
        for (int i = 0; i < sizes.length; i++) totalSize += sizes[i];
        
        int offset = totalSize / 2 - title.length() / 2;
        
        String format = verticalSeparator + "%-" + offset + "s%-" 
                + (totalSize - offset) + "s" + verticalSeparator + '\n';
        
        System.out.printf(format, "", title);
    }
    
    private void printHeaders(){
        System.out.printf(getFormat(), headers.toArray());
    }
    
    private void printRows(){
        printLine();
        for (int i = 0; i < rows.size(); i++) {
            adjustList(rows.get(i));
            System.out.printf(getFormat(), rows.get(i).toArray()); 
            printLine();
        }
    }
   
    public void print(){
        updateSizes();
        
        //print title
        if (title != null){
            printLine();
            printTitle();
            printLine();
        }
        
        //print headers
        if (headers != null){
            adjustList(headers);
            printHeaders();
        }
        
        // print rows
        printRows();
     
    }
    
    public static void printTest() {
        
        Table t = new Table("Title");
        
        t.setHeaders("Header 1", "Header 2", "Header 2");
        t.addRow("Nuno Fonseca", "Col 12");
        t.addRow("Col 21", "Col 22", "Col 23");
        t.addRow("Col 31", "Col 32", "Col 33");
        
        t.print();
    }
}
