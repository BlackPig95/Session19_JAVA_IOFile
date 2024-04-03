package baitap.config;

import java.io.*;
import java.util.List;

public class ReadAndWriteFile
{
    public static <T extends Serializable> List<T> readObjectStream(String path)
    {
        InputStream fileInputStream = null;
        ObjectInputStream objectInputStream = null;
        try
        {
            fileInputStream = new FileInputStream(path);
            objectInputStream = new ObjectInputStream(fileInputStream);
            return (List<T>) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e)
        {
            throw new RuntimeException(e);
        } finally
        {
            try
            {
                if (fileInputStream != null)
                {
                    fileInputStream.close();
                }
                if (objectInputStream != null)
                {
                    objectInputStream.close();
                }
            } catch (IOException e)
            {
                throw new RuntimeException(e);
            }
        }
    }

    //Overload sử dụng param File thay vì String
    public static <T extends Serializable> List<T> readObjectStream(File file)
    {
        InputStream fileInputStream = null;
        ObjectInputStream objectInputStream = null;
        try
        {
            fileInputStream = new FileInputStream(file);
            objectInputStream = new ObjectInputStream(fileInputStream);
            return (List<T>) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e)
        {
            throw new RuntimeException(e);
        } finally
        {
            try
            {
                if (fileInputStream != null)
                {
                    fileInputStream.close();
                }
                if (objectInputStream != null)
                {
                    objectInputStream.close();
                }
            } catch (IOException e)
            {
                throw new RuntimeException(e);
            }
        }
    }

    public static <T extends Serializable> void writeObjectStream(String path, List<T> tList)
    {
        FileOutputStream fileOutputStream = null;
        ObjectOutputStream objectOutputStream = null;
        try
        {
            fileOutputStream = new FileOutputStream(path);
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(tList);
        } catch (IOException e)
        {
            throw new RuntimeException(e);
        } finally
        {
            try
            {
                if (fileOutputStream != null)
                {
                    fileOutputStream.close();
                }
                if (objectOutputStream != null)
                {
                    objectOutputStream.close();
                }
            } catch (IOException e)
            {
                throw new RuntimeException(e);
            }
        }
    }

    //Overload sử dụng param File thay vì string
    public static <T extends Serializable> void writeObjectStream(File file, List<T> tList)
    {
        FileOutputStream fileOutputStream = null;
        ObjectOutputStream objectOutputStream = null;
        try
        {
            fileOutputStream = new FileOutputStream(file);
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(tList);
        } catch (IOException e)
        {
            throw new RuntimeException(e);
        } finally
        {
            try
            {
                if (fileOutputStream != null)
                {
                    fileOutputStream.close();
                }
                if (objectOutputStream != null)
                {
                    objectOutputStream.close();
                }
            } catch (IOException e)
            {
                throw new RuntimeException(e);
            }
        }
    }
}
