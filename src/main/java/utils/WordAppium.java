package utils;

import freemarker.log.Logger;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.*;

public class WordAppium {

  private static final Logger LOGGER = Logger.getLogger(WordAppium.class.getName());


  public static void main() {
    String existingDocumentPath =
            System.getProperty("user.dir") + File.separator + "ruta" + File.separator + "EXXO.docx";
    String capturasFolderPath = "Capturas/";
    String copiaFolderPath = "Copia/";

    File capturasFolder = new File(capturasFolderPath);
    File[] capturasFiles = capturasFolder.listFiles();

    if (capturasFiles == null || capturasFiles.length == 0) {
      LOGGER.info("No hay capturas para procesar.");
      return;
    }

    try (FileInputStream fis = new FileInputStream(existingDocumentPath);
         XWPFDocument document = new XWPFDocument(fis);
         FileOutputStream fos = new FileOutputStream(existingDocumentPath)) {

      XWPFParagraph paragraph = document.createParagraph();

      for (File capturaFile : capturasFiles) {
        addCapturaToWordDocument(paragraph, capturaFile);
      }

      document.write(fos);
      LOGGER.info("Capturas agregadas al documento existente.");

    } catch (IOException | InvalidFormatException e) {
      e.printStackTrace();
      return;
    }

    moveFilesToBackup(capturasFiles, copiaFolderPath);
  }

  private static void addCapturaToWordDocument(XWPFParagraph paragraph, File capturaFile)
          throws IOException, InvalidFormatException {
    try (FileInputStream fis = new FileInputStream(capturaFile)) {
      XWPFRun run = paragraph.createRun();
      run.addPicture(fis, Document.PICTURE_TYPE_PNG, capturaFile.getName(),
              Units.toEMU(134), Units.toEMU(250)); // Ajuste de tamaño directo en EMU
    }
  }

  private static void moveFilesToBackup(File[] files, String destinationFolder) {
    File destFolder = new File(destinationFolder);
    if (!destFolder.exists()) {
      destFolder.mkdirs();
    }

    for (File file : files) {
      try {
        Files.move(file.toPath(), new File(destFolder, file.getName()).toPath(),
                StandardCopyOption.REPLACE_EXISTING);
      } catch (IOException e) {
        System.err.println("Error al mover archivo: " + file.getName());
        e.printStackTrace();
      }
    }
    LOGGER.info("Archivos movidos a la carpeta de copia.");
  }
}