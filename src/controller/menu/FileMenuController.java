package controller.menu;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Group;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import model.Layer;
import model.LayerInfo;
import view.ApplicationMenu;
import view.Workspace;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.*;
import java.util.ArrayList;
/*
Pour imprimer: https://stackoverflow.com/questions/31100226/how-to-print-on-printer-image-using-javafx8
 */
public class FileMenuController {

    private ApplicationMenu am;
    private Workspace ws;

    private File pathToFile;

    public FileMenuController(ApplicationMenu am, Workspace ws) {
        this.am = am;
        this.ws = ws;
    }


    public void newFile() {

        FileChooser fc = new FileChooser();

        fc.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Images", "*.*"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
        );

        fc.setTitle("Nouveau projet à partir d'une image");

        File file = fc.showOpenDialog(this.am.getScene().getWindow());
        if (file != null) {
            System.out.println("On crÃ©er un projet et on affiche la vue");
            Image img = new Image(file.toURI().toString());
            ws.setImage(img);
        }
    }


    public void openProject() {
        FileChooser fc = new FileChooser();
        fc.setTitle("Ouvrir un projet");
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Projet BPEP (*.bpep)", "*.bpep"));

        File file = fc.showOpenDialog(am.getScene().getWindow());
        if (file != null) {
            try {
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));

                //Lecture image
                byte[] test = ois.readAllBytes();
                Image img = new Image(new ByteArrayInputStream(test));
                System.out.println("Image lue");

                //Lecture Layer
                ArrayList<LayerInfo> layersInfos = (ArrayList) ois.readObject();
                System.out.println("Calques lues");
                ws.openingProject(img, layersInfos);
            } catch (java.io.OptionalDataException e) {
                System.out.println("Erreur: " + e.getClass().getName());
                System.out.println("Données à lire: " + e.length + " bytes");
                System.out.println("Fin du fichier de sauvegarde: " + e.eof);
                e.printStackTrace();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Ouverture terminée.");
    }

    public void saveProject() {
        if (pathToFile == null) saveProjectAs();
        else {
            Image img = this.ws.getImgSource();

            if (pathToFile != null) {
                try {
                    FileOutputStream fop = new FileOutputStream(pathToFile);

                    ObjectOutputStream oos = new ObjectOutputStream(fop);

                    //Ecrire l'image
                    System.out.println("Ecriture de l'image");
                    ImageIO.write(SwingFXUtils.fromFXImage(img, null), "png", oos);

                    //Ecrire les différents arrays
                    ArrayList<LayerInfo> layersInfos = new ArrayList<LayerInfo>();
                    for (Layer l : this.ws.getLayers()) layersInfos.add(l.getInfo());
                    System.out.println("Ecriture des informations des layers");
                    oos.writeObject(layersInfos);

                    //Fin
                    oos.close();
                    fop.close();
                    System.out.println("Enregistrement terminé");
                } catch (Exception e) {
                    System.out.println(e.getClass().getName());
                }
            }
        }
    }


    public void saveProjectAs() {
        FileChooser fc = new FileChooser();
        fc.setTitle("Enregistrer sous");
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Projet BPEP (*.bpep)", "*.bpep"));

        File file = fc.showSaveDialog(am.getScene().getWindow());

        Image img = this.ws.getImgSource();

        if (file != null) {
            try {
                FileOutputStream fop = new FileOutputStream(file);

                ObjectOutputStream oos = new ObjectOutputStream(fop);

                //Ecrire l'image
                System.out.println("Ecriture de l'image");
                ImageIO.write(SwingFXUtils.fromFXImage(img, null), "png", oos);

                //Ecrire les différents arrays
                ArrayList<LayerInfo> layersInfos = new ArrayList<LayerInfo>();
                for (Layer l : this.ws.getLayers()) layersInfos.add(l.getInfo());
                System.out.println("Ecriture des informations des layers");
                oos.writeObject(layersInfos);

                //Fin
                oos.close();
                fop.close();
                pathToFile = file;
                System.out.println("Enregistrement terminé");
            } catch (Exception e) {
                System.out.println(e.getClass().getName());
            }
        }
    }

    public void printImage(){
        Group g = this.ws.getGroup();
        BufferedImage bufferedImg = SwingFXUtils.fromFXImage(g.snapshot(new SnapshotParameters(),null),null);
        PrinterJob printJob = PrinterJob.getPrinterJob();
        printJob.setPrintable(new Printable() {
            @Override
            public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
                // Get the upper left corner that it printable
                int x = (int) Math.ceil(pageFormat.getImageableX());
                int y = (int) Math.ceil(pageFormat.getImageableY());
                if (pageIndex != 0) {
                    return NO_SUCH_PAGE;
                }
                graphics.drawImage(bufferedImg, x, y, bufferedImg.getWidth(),bufferedImg.getHeight(), null);
                return PAGE_EXISTS;
            }
        });
        try {
            printJob.print();
        } catch (PrinterException e1) {
            e1.printStackTrace();
        }
    }
}
