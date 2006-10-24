/**
 * Mogwai Looks.
 * Copyright (C) 2002 The Mogwai Project.
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package de.mogwai.looks.components;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterJob;
import java.io.File;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import de.mogwai.looks.tools.DefaultModificationListener;

public class DefaultTextPane extends JTextPane implements Printable {

    private DefaultScrollPane scrollPane;

    protected PrinterJob printerjob = null;

    private HashMap<Integer, Integer> hOffSets = new HashMap<Integer, Integer>();

    private boolean bDebug = false;

    public DefaultTextPane() {

        scrollPane = new DefaultScrollPane(this);
        initialize();
    }

    public JScrollPane getScrollPane() {

        return scrollPane;
    }

    private void initialize() {

        getDocument()
                .addDocumentListener(new DefaultModificationListener(this));

        setDoubleBuffered(false);
    }

    /**
     * Print Method
     * 
     * @param g
     *            Graphics Context of Printer
     * @param pageFormat
     *            PageFormat
     * @param pageIndex
     *            PageIndex
     * @return Printable.PAGE_EXISTS / NO_SUCH_PAGE
     */
    public int print(Graphics g, PageFormat pageFormat, int pageIndex) {
        int iOldFormat = pageFormat.getOrientation();
        pageFormat = printerjob.defaultPage(pageFormat);
        pageFormat.setOrientation(iOldFormat);
        int dx = (int) pageFormat.getImageableX();
        int dy = (int) pageFormat.getImageableY();
        int dw = (int) pageFormat.getImageableWidth();
        int dh = (int) (pageFormat.getImageableHeight());
        double tw = this.getMinimumSize().getWidth();
        double th = this.getMinimumSize().getHeight();
        double dPageClippingPercent = 0.03;
        dh = (dh) - (int) (dh * dPageClippingPercent);
        double dZoom = dw / tw;
        int iCuttingEdge = 1;

        int iTranslatePlus = 0;
        for (int k = 0; k < pageIndex; k++) {
            if (hOffSets.containsKey(k))
                iTranslatePlus += hOffSets.get(k);
        } // for
        if (bDebug)
            System.out.println("Translation Offset: " + iTranslatePlus);
        int pageCount = ((int) (((th + iTranslatePlus) * dZoom) / dh)) + 1;

        if (pageIndex < pageCount) {
            // Prepaint in Buffered Image
            if (!hOffSets.containsKey(pageIndex)) {
                BufferedImage bImage = new BufferedImage(dw, dh,
                        BufferedImage.TYPE_INT_ARGB);
                Graphics2D gTemp = bImage.createGraphics();
                gTemp.scale(dZoom, dZoom);
                gTemp.setClip(0, 0, (int) ((dw / dZoom)), (int) ((dh / dZoom)));
                double dClipX = gTemp.getClipBounds().getX();
                int iOriginalClipY = (pageIndex * dh) - iTranslatePlus;
                double dClipY = gTemp.getClipBounds().getY() - (iOriginalClipY)
                        / dZoom;

                if (bDebug)
                    System.out.println("Original ClipY " + iOriginalClipY
                            + " Height= " + dh + " Full Height= " + th);
                gTemp.translate(dClipX, dClipY);
                gTemp.setColor(Color.WHITE);
                this.print(gTemp);
                gTemp.dispose();
                int iOffset = calculateOffset(bImage);
                hOffSets.put(pageIndex, iOffset);
                if (bDebug)
                    System.out.println("Offset for page " + pageIndex + " = "
                            + iOffset);
                try {
                    if (bDebug)
                        ImageIO.write(bImage, "png", new File("c:\\bufferimage"
                                + pageIndex + ".png"));
                } catch (Exception eX) {
                    eX.printStackTrace();
                } // try..catch
            } // if

            int iOffSet = hOffSets.containsKey(pageIndex) ? hOffSets.get(
                    pageIndex).intValue() : 0;
            // Print on Printer
            Graphics2D g2 = (Graphics2D) g;
            g2.scale(dZoom, dZoom);
            int iClipX = (int) ((dx / dZoom));
            int iClipY = (int) (((dy) / dZoom));
            int iClipW = (int) ((dw / dZoom));
            int iClipHOriginal = dh - iOffSet;
            iClipHOriginal = iClipHOriginal + iCuttingEdge;
            int iClipH = (int) ((iClipHOriginal / dZoom));
            if (bDebug)
                System.out.println("---> Clipping for " + pageIndex + " X:"
                        + iClipX + " Y:" + iClipY + " W:" + iClipW + " H:"
                        + iClipH + " OriginalH: " + iClipHOriginal);
            g2.setClip(iClipX, iClipY, iClipW, iClipH);
            double dClipX = g2.getClipBounds().getX();
            int iOriginalY = (pageIndex * dh) - iTranslatePlus;
            if (pageIndex != 0)
                iOriginalY = iOriginalY - iCuttingEdge;
            double dClipY = g2.getClipBounds().getY() - (iOriginalY) / dZoom;
            if (bDebug)
                System.out
                        .println("---> Translate for " + pageIndex + " X:"
                                + dClipX + " Y:" + dClipY + " OriginalY: "
                                + iOriginalY);
            g2.translate(dClipX, dClipY);
            this.print(g2);
            g2.translate(-dClipX, -dClipY);
            g2.setColor(Color.BLACK);
            g2.setFont(new Font("Sans Serif", Font.PLAIN, 8));
            String sPageIndex = "Seite " + (pageIndex + 1) + " von "
                    + pageCount;
            int iPageIndexWidth = g2.getFontMetrics().stringWidth(sPageIndex);
            int iPageIndexPos = ((iClipW) - iPageIndexWidth) / 2;
            int iNewClipHeight = (int) (iClipH * (1.02 + dPageClippingPercent));
            int iPageHPos = iClipY + iNewClipHeight;
            g2.setClip(iClipX, iClipY, iClipW, (iNewClipHeight));
            g2.drawString(sPageIndex, iClipX + iPageIndexPos, iPageHPos);
            return Printable.PAGE_EXISTS;
        }
        return Printable.NO_SUCH_PAGE;
    } // print

    /**
     * Offset Berechnung. Es wird von Unten an die erste vollkommen weisse zeile
     * innerhalb der letzten 10 prozent der Seite gesucht. wird diese gefunden,
     * wird dies als offset zurückgegeben. Wird in den letzten 10 Prozent keine
     * gefunden, so wird die letzte Zeile zurückgegeben, welche von einer Farbe
     * (die nicht! weiss ist) mindestens 80 Prozent füllt und dies wird als
     * offset zurückgegeben. Ist dort auch kein offset vorhanden, wird 0
     * zurückgegeben und hart umgebrochen. Funktioniert so allerdings nur bei
     * weisser hintergrundfarbe
     * 
     * @param image
     *            Image
     * @return offset für clipping area
     */
    private int calculateOffset(BufferedImage image) {
        int iHeight = image.getHeight();
        int iMaxBreakBorder = (int) ((double) (iHeight / 100) * 90);
        int iRGB = 0;
        int iIncrement = 0;
        int iLastPercentBorder = 0;
        int iWidth = image.getWidth();
        int iColorBorder = (int) ((double) (iWidth / 100) * 80);
        int y;
        outer: for (y = iHeight - 3; y >= 0; y--) {
            HashMap<Integer, Integer> hMap = new HashMap<Integer, Integer>();
            for (int x = 0; x < iWidth; x++) {
                iRGB = image.getRGB(x, y);
                iIncrement = 0;
                if (hMap.containsKey(iRGB))
                    iIncrement = hMap.get(iRGB);
                hMap.put(iRGB, iIncrement + 1);
            } // for x

            if (y < iMaxBreakBorder)
                return iLastPercentBorder;

            for (Integer iKey : hMap.keySet()) {
                int iTemp = hMap.get(iKey);
                if ((iTemp == iWidth) && (iKey.intValue() == 0xFFFFFFFF))
                    break outer;
                if ((iTemp >= iColorBorder) && (iKey.intValue() != 0xFFFFFFFF)) {
                    if (iLastPercentBorder == 0) {
                        if (bDebug)
                            System.out.println("Y Pos: " + y + " Height "
                                    + iHeight);
                        iLastPercentBorder = (iHeight - y) - 1;
                    } // if
                } // if
            } // for keys

        } // for y
        if (bDebug)
            System.out.println("---Y Pos: " + y + " Height " + iHeight);
        return (iHeight - y) - 1;
    } // calculateOffset

    /**
     * Print Method to call
     * 
     */
    public void printme() {
        final DefaultTextPane me = this;
        if (hOffSets != null)
            hOffSets.clear();

        printerjob = PrinterJob.getPrinterJob();
        printerjob.setPrintable(me);

        try {
            printerjob.print();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
