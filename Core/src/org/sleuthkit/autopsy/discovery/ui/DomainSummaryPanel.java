/*
 * Autopsy
 *
 * Copyright 2020 Basis Technology Corp.
 * Contact: carrier <at> sleuthkit <dot> org
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.sleuthkit.autopsy.discovery.ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.TimeZone;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import org.openide.util.NbBundle;
import org.sleuthkit.autopsy.coreutils.ThreadConfined;
import org.sleuthkit.autopsy.datamodel.ContentUtils;

/**
 * Class which displays a preview and details about a domain.
 */
class DomainSummaryPanel extends javax.swing.JPanel implements ListCellRenderer<DomainWrapper> {

    private static final long serialVersionUID = 1L;
    private static final Color SELECTION_COLOR = new Color(0, 120, 215);

    /**
     * Creates new form DomainPanel.
     */
    @ThreadConfined(type = ThreadConfined.ThreadType.AWT)
    DomainSummaryPanel() {
        initComponents();
        domainNameLabel.setFont(domainNameLabel.getFont().deriveFont(domainNameLabel.getFont().getStyle(), domainNameLabel.getFont().getSize() + 6));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        domainNameLabel = new javax.swing.JLabel();
        sampleImageLabel = new javax.swing.JLabel();
        numberOfImagesLabel = new javax.swing.JLabel();
        activityLabel = new javax.swing.JLabel();
        pagesLabel = new javax.swing.JLabel();
        filesDownloadedLabel = new javax.swing.JLabel();
        totalVisitsLabel = new javax.swing.JLabel();

        setBorder(javax.swing.BorderFactory.createEtchedBorder());

        sampleImageLabel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        sampleImageLabel.setIconTextGap(0);
        sampleImageLabel.setMaximumSize(new java.awt.Dimension(100, 100));
        sampleImageLabel.setMinimumSize(new java.awt.Dimension(100, 100));
        sampleImageLabel.setPreferredSize(new java.awt.Dimension(100, 100));

        org.openide.awt.Mnemonics.setLocalizedText(activityLabel, org.openide.util.NbBundle.getMessage(DomainSummaryPanel.class, "DomainSummaryPanel.activityLabel.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(pagesLabel, org.openide.util.NbBundle.getMessage(DomainSummaryPanel.class, "DomainSummaryPanel.pagesLabel.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(filesDownloadedLabel, org.openide.util.NbBundle.getMessage(DomainSummaryPanel.class, "DomainSummaryPanel.filesDownloadedLabel.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(totalVisitsLabel, org.openide.util.NbBundle.getMessage(DomainSummaryPanel.class, "DomainSummaryPanel.totalVisitsLabel.text")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(domainNameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 539, Short.MAX_VALUE)
                        .addGap(47, 47, 47))
                    .addComponent(activityLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pagesLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(filesDownloadedLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(totalVisitsLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(numberOfImagesLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(sampleImageLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(numberOfImagesLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(sampleImageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(domainNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(activityLabel)
                        .addGap(11, 11, 11)
                        .addComponent(totalVisitsLabel)
                        .addGap(11, 11, 11)
                        .addComponent(pagesLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(filesDownloadedLabel)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel activityLabel;
    private javax.swing.JLabel domainNameLabel;
    private javax.swing.JLabel filesDownloadedLabel;
    private javax.swing.JLabel numberOfImagesLabel;
    private javax.swing.JLabel pagesLabel;
    private javax.swing.JLabel sampleImageLabel;
    private javax.swing.JLabel totalVisitsLabel;
    // End of variables declaration//GEN-END:variables

    @ThreadConfined(type = ThreadConfined.ThreadType.AWT)
    @NbBundle.Messages({"# {0} - startDate",
        "# {1} - endDate",
        "DomainSummaryPanel.activity.text=Activity: {0} to {1}",
        "DomainSummaryPanel.pages.text=Page views in past 60 days: ",
        "DomainSummaryPanel.totalPages.text=Total page views: ",
        "DomainSummaryPanel.downloads.text=Files downloaded: ",
        "DomainSummaryPanel.loadingImages.text=Loading thumbnail..."})
    @Override
    public Component getListCellRendererComponent(JList<? extends DomainWrapper> list, DomainWrapper value, int index, boolean isSelected, boolean cellHasFocus) {
        domainNameLabel.setText(value.getResultDomain().getDomain());
        TimeZone timeZone = ContentUtils.getTimeZone(value.getResultDomain().getDataSource());
        String startDate = formatDate(value.getResultDomain().getActivityStart(), timeZone);
        String endDate = formatDate(value.getResultDomain().getActivityEnd(), timeZone);
        activityLabel.setText(Bundle.DomainSummaryPanel_activity_text(startDate, endDate));
        totalVisitsLabel.setText(Bundle.DomainSummaryPanel_totalPages_text() + value.getResultDomain().getTotalPageViews());
        pagesLabel.setText(Bundle.DomainSummaryPanel_pages_text() + value.getResultDomain().getPageViewsInLast60Days());
        filesDownloadedLabel.setText(Bundle.DomainSummaryPanel_downloads_text() + value.getResultDomain().getFilesDownloaded());
        if (value.getThumbnail() == null) {
            numberOfImagesLabel.setText(Bundle.DomainSummaryPanel_loadingImages_text());
            sampleImageLabel.setIcon(null);
        } else {
            numberOfImagesLabel.setText(null);
            sampleImageLabel.setIcon(new ImageIcon(value.getThumbnail()));
        }
        setBackground(isSelected ? SELECTION_COLOR : list.getBackground());
        return this;
    }
    
    /**
     * Formats an epoch time in a given time zone using the following pattern
     * 
     *      MMM dd YYYY
     * 
     * The pattern below is formatted manually to reuse the MonthAbbreviation utility.
     */
    private String formatDate(long epochSeconds, TimeZone timeZone) {
        Instant epochSecondsAsInstant = Instant.ofEpochSecond(epochSeconds);
        ZonedDateTime dateTime = ZonedDateTime.ofInstant(epochSecondsAsInstant, timeZone.toZoneId());
        MonthAbbreviation currentCutOffMonth = MonthAbbreviation.fromMonthValue(dateTime.getMonthValue());
        return String.format("%s %02d %04d", 
                currentCutOffMonth.toString(), 
                dateTime.getDayOfMonth(), dateTime.getYear());
    }

    @ThreadConfined(type = ThreadConfined.ThreadType.AWT)
    @Override
    public String getToolTipText(MouseEvent event) {
        if (event != null) {
            //gets tooltip of internal panel item mouse is over
            Point point = event.getPoint();
            for (Component comp : getComponents()) {
                if (DiscoveryUiUtils.isPointOnIcon(comp, point)) {
                    String toolTip = ((JComponent) comp).getToolTipText();
                    if (toolTip == null || toolTip.isEmpty()) {
                        return null;
                    } else {
                        return toolTip;
                    }
                }
            }
        }
        return null;
    }

}
