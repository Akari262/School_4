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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controller implements Initializable{

    @FXML
    public TableView<Classes> tbl;

    @FXML
    private TableColumn<Classes, Integer> nbr;

    @FXML
    private TableColumn<Classes, String> cls;

    @FXML
    private TableColumn<Classes, Integer> nbr_d;

    @FXML
    private TableColumn<Classes, Integer> nbr_s;

    ObservableList<Children> children = FXCollections.observableArrayList();

    private Stage stage;
    private Scene scene;
    ArrayList<String> ar = new ArrayList<String>();
    ArrayList<String> unicue = new ArrayList<String>();
    ArrayList<Integer> num1 = new ArrayList<Integer>();
    ObservableList<Classes> classes = FXCollections.observableArrayList();

    public int getNumber_fam(String clas) throws IOException {
        children.clear();
        unicue.clear();
        String file = "data.csv";
        BufferedReader reader = null;
        String line = "";
        int x = 0;
        try {
            reader = new BufferedReader(new FileReader(file));

            while((line = reader.readLine()) != null) {
                String[] row = line.split(";");
                if(row[7].equals(clas)) {
                    if( !unicue.contains(row[5])){
                        unicue.add(row[5]);
                    }
                };
            }
            x = unicue.size();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            reader.close();
        }
        return x;
    }
    public int getNumber_ch(String clas) throws IOException {
        children.clear();
        String file = "data.csv";
        BufferedReader reader = null;
        String line = "";
        int x = 0;
        try {
            reader = new BufferedReader(new FileReader(file));

            while((line = reader.readLine()) != null) {
                String[] row = line.split(";");
                if(row[7].equals(clas)) {
                    x++;
                };
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            reader.close();
        }
        return x;
    }
    public void arrClasses() throws IOException {
        classes.clear();
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
                    Classes clas = new Classes(x, index, getNumber_ch(index), getNumber_fam(index));
                    classes.add(clas);
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

    public void arrWrClasses() throws IOException {
        File file = new File("class.csv");
        /*if(file.delete()){
            file.createNewFile();
        }else{
            System.out.println("Delete error");
        }*/

        try (PrintWriter out = new PrintWriter("class.csv")){
            for(Classes tbl : classes){
                out.write(tbl.getClasses() + ";");
            }

        } catch (FileNotFoundException e) {
            System.out.print("File not found");
        }


    }


    @FXML
    protected void onow1() throws IOException {
        arrClasses();
    }

    @FXML
    public TextField classTextField;

    String className;

    @FXML
    protected void addClass() throws IOException {
        className = classTextField.getText();
        className = className.replace(";",",");
        arrClasses();
        Classes clas = new Classes(0, className, 0, 0);
        classes.add(clas);
        arrWrClasses();
        arrClasses();
        classTextField.clear();
    }


    @FXML
    protected void switchToScene(ActionEvent event) throws IOException {
        onow1();
        Parent fxmlLoader = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader);
        stage.setScene(scene);
        stage.show();
        arrWrClasses();
    }

    @FXML
    protected void delete(ActionEvent event) throws IOException {
            int selectedID = tbl.getSelectionModel().getSelectedIndex();
            if (selectedID == -1){
                System.out.println("Element not selected");
            }
            else {
                tbl.getItems().remove(selectedID);
                arrWrClasses();
            }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            arrClasses();
        } catch (IOException e) {
            e.printStackTrace();
        };
        nbr.setCellValueFactory(new PropertyValueFactory<Classes, Integer>("num"));
        cls.setCellValueFactory(new PropertyValueFactory<Classes, String>("classes"));
        nbr_d.setCellValueFactory(new PropertyValueFactory<Classes, Integer>("num_d"));
        nbr_s.setCellValueFactory(new PropertyValueFactory<Classes, Integer>("num_s"));

        tbl.setItems(classes);
    }
}