package sample;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.text.Font;
import javafx.scene.control.MenuItem;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;

public class Controller {
    public TextArea texto;
    public CheckMenuItem fcblue;
    public CheckMenuItem fcred;
    public CheckMenuItem fcgreen;
    public CheckMenuItem ftarial;
    public CheckMenuItem fttimesnewroman;
    public CheckMenuItem ftcomicsans;
    public CheckMenuItem tamano8;
    public CheckMenuItem tamano13;
    public CheckMenuItem tamano22;
    public MenuItem ayuda;
    public MenuItem copiar;
    public MenuItem copiarContext;
    public MenuItem cortar;
    public MenuItem cortarContext;
    public MenuItem abrir;
    public Stage stage;

    public void salir(){
        Platform.exit();
    }
    public void copiar(){
        texto.copy();
    }
    public void cortar(){
        texto.cut();
    }
    public void pegar(){
        texto.paste();
    }
    public void deshacer(){
        texto.undo();
    }
    public void borrar(){
        texto.clear();
    }
    public void tamano(javafx.event.ActionEvent actionEventTamano){
        tamano8.setSelected(false);
        tamano13.setSelected(false);
        tamano22.setSelected(false);
        if (actionEventTamano.getSource().equals(tamano8)==true){
            tamano8.setSelected(true);
            texto.setFont(Font.font(8));
        }
        if (actionEventTamano.getSource().equals(tamano13)==true){
            tamano13.setSelected(true);
            texto.setFont(Font.font(13));
        }
        if (actionEventTamano.getSource().equals(tamano22)==true){
            tamano22.setSelected(true);
            texto.setFont(Font.font(22));
        }
    }
    public void colorFuente(javafx.event.ActionEvent actionEventColor) {
        fcblue.setSelected(false);
        fcred.setSelected(false);
        fcgreen.setSelected(false);
        //System.out.println("0 "+actionEvent.getSource());
        //System.out.println("1 "+actionEvent.getSource().equals(fcblue));
        if (actionEventColor.getSource().equals(fcblue)==true){
            fcblue.setSelected(true);
            texto.setStyle("-fx-text-fill: blue;");
        }
        if (actionEventColor.getSource().equals(fcred)==true){
            fcred.setSelected(true);
            texto.setStyle("-fx-text-fill: red;");
        }
        if (actionEventColor.getSource().equals(fcgreen)==true){
            fcgreen.setSelected(true);
            texto.setStyle("-fx-text-fill: green;");
        }
    }
    public void tipoFuente(javafx.event.ActionEvent actionEventFuente) {
        ftarial.setSelected(false);
        fttimesnewroman.setSelected(false);
        ftcomicsans.setSelected(false);
        Double tamanoTexto = texto.getFont().getSize();
        if (actionEventFuente.getSource().equals(ftarial)==true){
            ftarial.setSelected(true);
            texto.setFont(Font.font("Arial", tamanoTexto));
        }
        if (actionEventFuente.getSource().equals(fttimesnewroman)==true){
            fttimesnewroman.setSelected(true);
            texto.setFont(Font.font("Times New Roman", tamanoTexto));
        }
        if (actionEventFuente.getSource().equals(ftcomicsans)==true){
            ftcomicsans.setSelected(true);
            texto.setFont(Font.font("Comic Sans", tamanoTexto));
        }
    }
    public void ayuda(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Ayuda: Informacion");
        alert.setHeaderText(null);
        alert.setContentText("Editor de texto basico creado por oscarXIII\n\tCierra la ventana para continuar.");
        alert.showAndWait();
        //http://code.makery.ch/blog/javafx-dialogs-official/
    }
    public void habilitar(){
        if (texto.getSelectedText().equals("")){
            copiar.setDisable(true);
            copiarContext.setDisable(true);
            cortar.setDisable(true);
            cortarContext.setDisable(true);
        } else {
            copiar.setDisable(false);
            copiarContext.setDisable(false);
            cortar.setDisable(false);
            cortarContext.setDisable(false);
        }
    }
    public void abrirArchivo() throws Exception {
        FileChooser abrirArchivo = new FileChooser();
        extnsionesArchivos(abrirArchivo);
        abrirArchivo.setTitle("Abrir archivo...");

        File archivoAbierto = abrirArchivo.showOpenDialog(stage);
        FileReader archivoEditar = new FileReader(archivoAbierto);
        BufferedReader BrArchivo =  new BufferedReader(archivoEditar);
        //BufferedReader br =  new BufferedReader(new FileReader(abrirArchivo.showOpenDialog(stage))); //ASI NO FUNCIONA!!!

        String textoDoc;
        while((textoDoc = BrArchivo.readLine())!=null) {
            texto.setText(texto.getText()+"\n"+textoDoc);
        }
        BrArchivo.close();
    }
    public void guardarArchivo() throws IOException {
        FileChooser guardarArchivo = new FileChooser();
        extnsionesArchivos(guardarArchivo);
        guardarArchivo.setTitle("Guardar archivo como...");

        File archivoGuardado = guardarArchivo.showSaveDialog(stage);
        FileWriter archivoEditado = new FileWriter(archivoGuardado);
        //PrintWriter PwArchivo = new PrintWriter(archivoEditado);
        BufferedWriter BwArchivo = new BufferedWriter(archivoEditado);
        String docTexto = texto.getText();
        BwArchivo.write(docTexto);
        BwArchivo.close();
    }
    private static void extnsionesArchivos(final FileChooser escojerArchivo) {
        escojerArchivo.setInitialDirectory(new File(System.getProperty("user.home")));
        escojerArchivo.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Todos", "*.*"),
                new FileChooser.ExtensionFilter("TXT", "*.txt"),
                new FileChooser.ExtensionFilter("NFO", "*.nfo")
        );
    }
}