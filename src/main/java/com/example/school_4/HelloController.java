package com.example.school_4;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

public class HelloController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent fxmlLoader;

    ArrayList<String> ar = new ArrayList<String>();
    ArrayList<Integer> num1 = new ArrayList<Integer>();
    ObservableList<com.example.school_4.Children> children = FXCollections.observableArrayList();

    @FXML
    public TextField my_name;

    @FXML
    public TextField adress;

    @FXML
    public TextField family_name;

    @FXML
    public TextField my_clas;

    @FXML
    public TextField harchi;

    @FXML
    public TextField phone_numb;

    @FXML
    public TextField document;


    @FXML
    private TableView<Children> tabl;

    @FXML
    private TableColumn<Children, String> adres;

    @FXML
    private TableColumn<Children, String> category;

    @FXML
    private TableColumn<Children, String> clas;

    @FXML
    private TableColumn<Children, String> doc;

    @FXML
    private TableColumn<Children, String> harch;

    @FXML
    private TableColumn<Children, String> name;

    @FXML
    private TableColumn<Children, Integer> num;

    @FXML
    private TableColumn<Children, String> parents_name;

    @FXML
    private TableColumn<Children, String> phone_num;


    public void arrClasses() throws IOException {
        String file = "class.csv";
        BufferedReader reader = null;
        String line = "";

        try {
            reader = new BufferedReader(new FileReader(file));
            int x = 0;
            while((line = reader.readLine()) != null){

                String [] row = line.split(";");
                ar.clear();
                num1.clear();
                for(String index : row) {
                    ar.add(index);
                    num1.add(x);
                    x++;
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            reader.close();
        }
    }

    public void arrChildren() throws IOException {
        children.clear();
        String file = "data.csv";
        BufferedReader reader = null;
        String line = "";

        try {
            reader = new BufferedReader(new FileReader(file));
            int x = 0;
            while((line = reader.readLine()) != null){
                String [] row = line.split(";");
                x++;
                Children child = new Children(x, row[0], row[1], row[2], row[3], row[4], row[5], row[6], row[7]);
                children.add(child);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            reader.close();
        }
    }

    @FXML
    protected void onovlennya(ActionEvent event) throws IOException {
        arrChildren();
        arrClasses();
    }

    public void onov() throws IOException {
        arrChildren();
        arrClasses();
    }

    @FXML
    protected void switchToScene2(ActionEvent event) throws IOException {
        onov();
        Parent fxmlLoader = FXMLLoader.load(getClass().getResource("scene.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader);
        stage.setScene(scene);
        stage.show();
    }

    public void arrWrChildren() throws IOException {
        File file = new File("data.csv");
        /*if(file.delete()){
            file.createNewFile();
        }else{
            System.out.println("Delete error");
        }*/

        try (PrintWriter out = new PrintWriter("data.csv")){
            for(Children tbl : children){

                out.write(tbl.getName() + ";" + tbl.getClas() + ";" + tbl.getAdres() + ";" + tbl.getHarch() + ";" + tbl.getParents_name() + ";" + tbl.getPhone_num() + ";" + tbl.getDoc() + ";" + tbl.getCategory() + ";" +"\n");
            }

        } catch (FileNotFoundException e) {
            System.out.print("File not found");
        }
    }


    @FXML
    protected void druk(ActionEvent event) throws IOException {
        onov();
        File file = new File("table.xlsx");
        if(file.delete()){
            System.out.println("Delete +");
        }else{
            System.out.println("Delete error");
        }

        Workbook workbook = new XSSFWorkbook();

        Sheet sheet = workbook.createSheet("Sheets");
        FileOutputStream fout = new FileOutputStream(file);

        Row row = null;
        Cell cell = null;

        int x = 0;
        int y = 0;

        for(String ar : ar){
            row = sheet.createRow(x);
            cell = row.createCell(0);
            cell.setCellValue(ar);
            x++;
            for(Children tbl : children){
                if(tbl.getCategory().equals(ar)){
                    row = sheet.createRow(x);
                    cell = row.createCell(0);
                    cell.setCellValue(Integer.toString(tbl.getNum()));
                    cell = row.createCell(1);
                    cell.setCellValue(tbl.getName());
                    cell = row.createCell(2);
                    cell.setCellValue(tbl.getClas());
                    cell = row.createCell(3);
                    cell.setCellValue(tbl.getAdres());
                    cell = row.createCell(4);
                    cell.setCellValue(tbl.getHarch());
                    cell = row.createCell(5);
                    cell.setCellValue(tbl.getParents_name());
                    cell = row.createCell(6);
                    cell.setCellValue(tbl.getPhone_num());
                    cell = row.createCell(7);
                    cell.setCellValue(tbl.getDoc());
                    x++;

                };
            }

        }

        workbook.write(fout);
        fout.close();

    }
    @FXML
    protected void in_new_year(ActionEvent event) throws IOException {
        children.clear();
        String file = "data.csv";
        BufferedReader reader = null;
        String line = "";

        try {
            reader = new BufferedReader(new FileReader(file));
            int x = 0;
            while((line = reader.readLine()) != null){
                String [] row = line.split(";");
                x++;
                row[1] = row[1].replace("11-","Вибув-");
                row[1] = row[1].replace("9-","100-");
                row[1] = row[1].replace("8-","9-");
                row[1] = row[1].replace("7-","8-");
                row[1] = row[1].replace("6-","7-");
                row[1] = row[1].replace("5-","6-");
                row[1] = row[1].replace("4-","5-");
                row[1] = row[1].replace("3-","4-");
                row[1] = row[1].replace("2-","3-");
                row[1] = row[1].replace("1-","2-");
                row[1] = row[1].replace("10-","11-");
                row[1] = row[1].replace("100-","10-");
                Children child = new Children(x, row[0], row[1], row[2], row[3], row[4], row[5], row[6], row[7]);
                children.add(child);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            reader.close();
        }
        arrWrChildren();
    }

    @FXML
    protected void vidmina(ActionEvent event) throws IOException {
        children.clear();
        String file = "data.csv";
        BufferedReader reader = null;
        String line = "";

        try {
            reader = new BufferedReader(new FileReader(file));
            int x = 0;
            while((line = reader.readLine()) != null){
                String [] row = line.split(";");
                x++;
                row[1] = row[1].replace("10-","100-");
                row[1] = row[1].replace("11-","10-");
                row[1] = row[1].replace("Вибув-","11-");
                row[1] = row[1].replace("2-","1-");
                row[1] = row[1].replace("3-","2-");
                row[1] = row[1].replace("4-","3-");
                row[1] = row[1].replace("5-","4-");
                row[1] = row[1].replace("6-","5-");
                row[1] = row[1].replace("7-","6-");
                row[1] = row[1].replace("8-","7-");
                row[1] = row[1].replace("9-","8-");
                row[1] = row[1].replace("100-","9-");
                Children child = new Children(x, row[0], row[1], row[2], row[3], row[4], row[5], row[6], row[7]);
                children.add(child);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            reader.close();
        }
        arrWrChildren();
    }

    @FXML
    protected void vulychennya(ActionEvent event) throws IOException {
        children.clear();
        String file = "data.csv";
        BufferedReader reader = null;
        String line = "";

        try {
            reader = new BufferedReader(new FileReader(file));
            int x = 0;
            while((line = reader.readLine()) != null){
                String [] row = line.split(";");
                x++;
                if (row[1].contains("Вибув")){
                    continue;
                };
                Children child = new Children(x, row[0], row[1], row[2], row[3], row[4], row[5], row[6], row[7]);
                children.add(child);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            reader.close();
        }
        arrWrChildren();
    }

    @FXML
    protected void delete(ActionEvent event) throws IOException {
        int selectedID = tabl.getSelectionModel().getSelectedIndex();
        if (selectedID == -1){
            System.out.println("Element not selected");
        }
        else {
            tabl.getItems().remove(selectedID);
            arrWrChildren();
        }
    }

    @FXML
    protected ChoiceBox<String> choice_class;

    String my_namet;
    String my_clast;
    String adresst;
    String harchit;
    String family_namet;
    String phone_numbt;
    String documentt;
    String choices;


    @FXML
    protected void addChild() throws IOException {
        my_namet = my_name.getText();
        my_namet = my_namet.replace(";",",");
        my_clast = my_clas.getText();
        my_clast = my_clast.replace(";",",");
        adresst = adress.getText();
        adresst = adresst.replace(";",",");
        harchit = harchi.getText();
        harchit = harchit.replace(";",",");
        family_namet = family_name.getText();
        family_namet = family_namet.replace(";",",");
        phone_numbt = phone_numb.getText();
        phone_numbt = phone_numbt.replace(";",",");
        documentt = document.getText();
        documentt = documentt.replace(";",",");
        choices = choice_class.getValue();
        Children child = new Children(0, my_namet, my_clast, adresst, harchit, family_namet, phone_numbt, documentt, choices);
        children.add(child);
        arrWrChildren();
        arrChildren();
        my_name.clear();
        my_clas.clear();
        adress.clear();
        harchi.clear();
        family_name.clear();
        phone_numb.clear();
        document.clear();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            arrChildren();
        } catch (IOException e) {
            e.printStackTrace();
        };
        num.setCellValueFactory(new PropertyValueFactory<Children, Integer>("num"));
        name.setCellValueFactory(new PropertyValueFactory<Children, String>("name"));
        clas.setCellValueFactory(new PropertyValueFactory<Children, String>("clas"));
        adres.setCellValueFactory(new PropertyValueFactory<Children, String>("adres"));
        harch.setCellValueFactory(new PropertyValueFactory<Children, String>("harch"));
        parents_name.setCellValueFactory(new PropertyValueFactory<Children, String>("parents_name"));
        phone_num.setCellValueFactory(new PropertyValueFactory<Children, String>("phone_num"));
        doc.setCellValueFactory(new PropertyValueFactory<Children, String>("doc"));
        category.setCellValueFactory(new PropertyValueFactory<Children, String>("category"));

        try {
            arrClasses();
        } catch (IOException e) {
            e.printStackTrace();
        }
        choice_class.getItems().addAll(ar);
        tabl.setItems(children);

    }
}