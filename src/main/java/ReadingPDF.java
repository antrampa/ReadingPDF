//package src;

import java.io.File;
import java.io.IOException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.encryption.AccessPermission;
import org.apache.pdfbox.text.PDFTextStripper;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.io.File;
import java.io.IOException;


public class ReadingPDF {
    public static void main(String args[]) throws IOException {
            System.out.println("Hi!");

            String fileName = "Aineite_ton_Kyrio.pdf";
            if (args.length != 1) {
                // usage();
            } else {
                fileName = args[0];
            }


            try {
                PDDocument document = PDDocument.load(new File(fileName));
                AccessPermission ap = document.getCurrentAccessPermission();
                if (!ap.canExtractContent())
                {
                    throw new IOException("You do not have permission to extract text");
                }

                PDFTextStripper stripper = new PDFTextStripper();

                // This example uses sorting, but in some cases it is more useful to switch it off,
                // e.g. in some files with columns where the PDF content stream respects the
                // column order.
                stripper.setSortByPosition(true);

                for (int p = 1; p <= document.getNumberOfPages(); ++p)
                {
                    // Set the page interval to extract. If you don't, then all pages would be extracted.
                    stripper.setStartPage(p);
                    stripper.setEndPage(p);

                    // let the magic happen
                    String text = stripper.getText(document);

                    // do some nice output with a header
                    String pageStr = String.format("page %d:", p);
                    System.out.println(pageStr);
                    for (int i = 0; i < pageStr.length(); ++i)
                    {
                        System.out.print("-");
                    }
                    System.out.println();
                    System.out.println(text.trim());
                    System.out.println();

                    // If the extracted text is empty or gibberish, please try extracting text
                    // with Adobe Reader first before asking for help. Also read the FAQ
                    // on the website:
                    // https://pdfbox.apache.org/2.0/faq.html#text-extraction
                }
            } catch (Exception ex) {
                System.err.println(ex.getMessage());
            }

//        //Loading an existing document
//        File file = new File("C:/PdfBox_Examples/new.pdf");
//        PDDocument document = PDDocument.load(file);
//
//        //Instantiate PDFTextStripper class
//        PDFTextStripper pdfStripper = new PDFTextStripper();
//
//        //Retrieving text from PDF document
//        String text = pdfStripper.getText(document);
//        System.out.println(text);
//
//        //Closing the document
//        document.close();

    }

    private static void usage()
    {
        System.err.println("Usage: java " + ReadingPDF.class.getName() + " <input-pdf>");
        System.exit(-1);
    }
}
