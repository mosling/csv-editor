package net.msk.utils.io;


import javax.swing.filechooser.FileFilter;
import java.io.File;


public class ExtensionFileFilter extends FileFilter
{
    private static String extension;
    private String description;

    /**
     * Creates a new instance of ExtensionFileFilter
     */
    public ExtensionFileFilter(String ext, String desc)
    {
        extension = ext;
        description = desc;
    }

    public String getDescription()
    {
        return description;
    }

    public boolean accept(File f)
    {
        if (f.isDirectory())
        {
            return true;
        }
        String s = f.getName();
        String fileextension = null;
        int i = s.lastIndexOf('.');
        if (i > 0 && i < s.length() - 1)
        {
            fileextension = s.substring(i + 1).toLowerCase();
        }
        if (fileextension != null)
        {
            if (fileextension.equals(extension)) return true;
            else return false;
        }
        return false;
    }

}
