package fr.univ_smb.cheapsprint.utilities;

import android.content.Context;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class FileSaveHandler {

    // https://stackoverflow.com/questions/40168601/android-how-to-save-json-data-in-a-file-and-retrieve-it
    public static String read(Context context, String fileName) {
        try {
            FileInputStream fis = context.openFileInput(fileName);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader bufferedReader = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
            }
            return sb.toString();
        } catch (FileNotFoundException fileNotFound) {
            return null;
        } catch (IOException ioException) {
            return null;
        }
    }

    public static boolean create(Context context, String fileName, String jsonString){
        try {
            FileOutputStream fos = context.openFileOutput(fileName,Context.MODE_PRIVATE);
            if (jsonString != null) {
                fos.write(jsonString.getBytes());
            }
            fos.close();
            return true;
        } catch (FileNotFoundException fileNotFound) {
            return false;
        } catch (IOException ioException) {
            return false;
        }

    }

    public static boolean isFilePresent(Context context, String fileName) {
        String path = context.getFilesDir().getAbsolutePath() + "/" + fileName;
        File file = new File(path);
        return file.exists();
    }

    public static ArrayList<String> ScanDirLists(Context context){
        String[] liste = context.fileList();
        ArrayList<String> list = new ArrayList<>();
        for (String s : liste) {
            if(!s.equals("instant-run"))
                list.add(s);
        }
        return list;
    }

    public static boolean removeFile(Context context, String fileName){
        if (isFilePresent(context, fileName)) {
            boolean deleted = context.deleteFile(fileName);
            return !isFilePresent(context, fileName) && deleted;
        }
        return false;
    }

}