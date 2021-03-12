package net.msk.utils;

import java.io.*;
import java.util.Vector;

public class FileUtils
{
    /**
     * Some tools for file handling
     */

    private static StringBuffer newLine = null;

    static
    {
        newLine = new StringBuffer();
        newLine.append(new char[]{13, 10});
    }

    public static StringBuffer getNewLine()
    {
        return newLine;
    }


    /**
     * Asserts that the given directory is existing
     *
     * @param dirName the full directory name
     * @throws IOException
     */
    public static File assertDirectory(String dirName)
        throws IOException
    {
        File dir = new File(dirName);
        if (dir.isDirectory() && dir.exists()) return dir;

        dir.mkdirs();
        if (!(dir.isDirectory() && dir.exists()))
            throw new IOException("Could not create directory '" + dirName + "' !");
        return dir;
    }

    /**
     * removes the Directory (recursive)
     *
     * @param dirName
     * @throws Exception
     */
    public static void rmDir(String dirName)
        throws Exception
    {
        File[] files = getFiles(dirName, null);
        for (int i = 0; i < files.length; i++)
        {
            File file = files[i];
            if (file.isDirectory())
                rmDir(file.getName());
            else
                file.delete();
        }
        File dir = new File(dirName);
        if (dir != null && dir.isDirectory() && dir.exists()) dir.delete();
    }

    /**
     * Asserts that the given file is not existing yet
     *
     * @param fileLocation the file
     * @throws IOException thrown when the file already exist
     */
    public static void assertFileNotExist(String fileLocation, boolean force)
        throws IOException
    {
        File file = new File(fileLocation);
        if (file.exists())
            if (!force || (!file.delete()))
                throw new IOException("File '" + fileLocation + "' already existing!");
    }

    /**
     * writes file
     *
     * @param fileName
     * @param content
     * @throws IOException
     */
    public static void writeFile(String fileName, StringBuffer content) throws IOException
    {
        FileWriter fileWriter = new FileWriter(fileName);
        BufferedWriter bw = new BufferedWriter(fileWriter);

        bw.write(content.toString());

        bw.flush();
        fileWriter.flush();
        bw.close();
        fileWriter.close();

    }

    /**
     * get array of files
     *
     * @param directoryName
     * @param fileExtension
     * @return filearray
     * @throws Exception
     */
    public static File[] getFiles(String directoryName, String fileExtension) throws Exception
    {

        //check for existing of the requested directory
        File directory = assertDirectory(directoryName);
        if (!directory.isDirectory())
            throw new IOException("Can't load filelist because directory '" + directoryName + "' not found.");

        //write all files within the given directory in the File-Array
        String ext = null;
        if (fileExtension != null)
            ext = fileExtension.toLowerCase();

        final String fileExtensionLower = ext;
        File[] fileArray = null;
        fileArray = directory.listFiles(new FilenameFilter()
        {
            public boolean accept(File dir, String name)
            {
                String nameLower = name.toLowerCase();
                if (fileExtensionLower == null || nameLower.endsWith("." + fileExtensionLower))
                {
                    return true;
                } else
                {
                    return false;
                }
            }
        });

        if (fileExtension != null)
            return fileArray;
        else
        {
            // remove directories
            Vector files = new Vector();
            for (int i = 0; i < fileArray.length; i++)
            {
                File file = fileArray[i];
                if (!file.isDirectory())
                {
                    files.add(file);
                }
            }
            File[] array2 = new File[files.size()];
            for (int i = 0; i < files.size(); i++)
            {
                File file = (File) files.elementAt(i);
                array2[i] = file;
            }
            return array2;
        }

    }

}
