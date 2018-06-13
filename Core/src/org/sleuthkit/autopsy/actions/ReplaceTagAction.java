/*
 * Autopsy Forensic Browser
 *
 * Copyright 2018 Basis Technology Corp.
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
package org.sleuthkit.autopsy.actions;

import java.awt.event.ActionEvent;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.logging.Level;
import javax.swing.AbstractAction;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import org.openide.util.NbBundle;
import org.openide.util.actions.Presenter;
import org.sleuthkit.autopsy.casemodule.Case;
import org.sleuthkit.autopsy.casemodule.NoCurrentCaseException;
import org.sleuthkit.autopsy.casemodule.services.TagsManager;
import org.sleuthkit.autopsy.coreutils.Logger;
import org.sleuthkit.datamodel.Tag;
import org.sleuthkit.datamodel.TagName;
import org.sleuthkit.datamodel.TskCoreException;
import org.sleuthkit.datamodel.TskData;

/**
 * Abstract class to define context action to replace a tag with another
 *
 * @param <T> tag type
 */
@NbBundle.Messages({
    "ReplaceTagAction.replaceTag=Replace Selected Tag(s) With"
})
abstract class ReplaceTagAction<T extends Tag> extends AbstractAction implements Presenter.Popup {

    private static final long serialVersionUID = 1L;
    protected static final String MENU_TEXT = NbBundle.getMessage(ReplaceTagAction.class,
            "ReplaceTagAction.replaceTag");

    ReplaceTagAction(String menuText) {
        super(menuText);
    }

    /**
     * Subclasses of replaceTagAction should not override actionPerformed, 
     * but instead override replaceTag.
     * 
     * @param event 
     */
    @Override
    @SuppressWarnings("NoopMethodInAbstractClass")
    public void actionPerformed(ActionEvent event) {
    }

    protected String getActionDisplayName() {
        return MENU_TEXT;
    }

    /**
     * Method to actually replace the selected tag with the given new tag
     *
     * @param oldTag
     * @param newTagName
     */
    abstract protected void replaceTag(T oldTag, TagName newTagName);

    /**
     * Returns elected tags which are to be replaced
     *
     * @return
     */
    abstract Collection<? extends T> getTagsToReplace();

   
    @Override
    public JMenuItem getPopupPresenter() {
        return new ReplaceTagMenu();
    }

    /**
     * Instances of this class implement a context menu user interface for
     * selecting a tag name to replace the tag with
     */
    private final class ReplaceTagMenu extends JMenu {

        private static final long serialVersionUID = 1L;

        ReplaceTagMenu() {
            super(getActionDisplayName());

            final Collection<? extends T> selectedTags = getTagsToReplace();
            
            // Get the current set of tag names.
            Map<String, TagName> tagNamesMap = null;
            try {
                TagsManager tagsManager = Case.getCurrentCaseThrows().getServices().getTagsManager();
                tagNamesMap = new TreeMap<>(tagsManager.getDisplayNamesToTagNamesMap());
            } catch (TskCoreException | NoCurrentCaseException ex) {
                Logger.getLogger(ReplaceTagMenu.class.getName()).log(Level.SEVERE, "Failed to get tag names", ex); //NON-NLS
            }

            // Ideally we should'nt allow user to pick a replacement tag that's already been applied to an item
            // In the very least we don't allow them to pick the same tag as the one they are trying to replace
            Set<String> existingTagNames = new HashSet<>();
            if (!selectedTags.isEmpty()) {
                T firstTag = selectedTags.iterator().next();
                existingTagNames.add(firstTag.getName().getDisplayName());
            }
            
            if (null != tagNamesMap && !tagNamesMap.isEmpty()) {
                for (Map.Entry<String, TagName> entry : tagNamesMap.entrySet()) {
                    String tagDisplayName = entry.getKey();
                    String notableString = entry.getValue().getKnownStatus() == TskData.FileKnown.BAD ? TagsManager.getNotableTagLabel() : "";
                    JMenuItem tagNameItem = new JMenuItem(tagDisplayName + notableString);
                    // for the bookmark tag name only, added shortcut label
                    if (tagDisplayName.equals(NbBundle.getMessage(AddTagAction.class, "AddBookmarkTagAction.bookmark.text"))) {
                        tagNameItem.setAccelerator(AddBookmarkTagAction.BOOKMARK_SHORTCUT);
                    }

                    // Add action to replace the tag
                    tagNameItem.addActionListener((ActionEvent event) -> {
                        selectedTags.forEach((oldtag) -> {
                            replaceTag(oldtag, entry.getValue());
                        });
                    });

                    // Don't allow replacing a tag with same tag.
                    if (existingTagNames.contains(tagDisplayName)) {
                        tagNameItem.setEnabled(false);
                    }
                    
                    add(tagNameItem);
                }
            } else {
                JMenuItem empty = new JMenuItem(NbBundle.getMessage(this.getClass(), "AddTagAction.noTags"));
                empty.setEnabled(false);
                add(empty);
            }

            addSeparator();
            JMenuItem newTagMenuItem = new JMenuItem(NbBundle.getMessage(this.getClass(), "AddTagAction.newTag"));
            newTagMenuItem.addActionListener((ActionEvent event) -> {
                TagName newTagName = GetTagNameDialog.doDialog();
                if (null != newTagName) {
                    selectedTags.forEach((oldtag) -> {
                        replaceTag(oldtag, newTagName);
                    });
                }
            });
            add(newTagMenuItem);

        }
    }
}
