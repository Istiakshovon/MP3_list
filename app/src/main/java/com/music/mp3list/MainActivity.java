package com.music.mp3list;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<HashMap<String,String>> songList=getPlayList("/storage/");
//        if(songList!=null){
//            for(int i=0;i<songList.size();i++){
//                String fileName=songList.get(i).get("file_name");
//                String filePath=songList.get(i).get("file_path");
//                //here you will get list of file name and file path that present in your device
//                Log.e("file details "," name ="+fileName +" path = "+filePath);
//            }
//        }else{
//            Toast.makeText(this, "No song", Toast.LENGTH_SHORT).show();
//        }

    }

        ArrayList<HashMap<String,String>> getPlayList(String rootPath) {
            ArrayList<HashMap<String,String>> fileList = new ArrayList<>();


            try {
                File rootFolder = new File(rootPath);
                File[] files = rootFolder.listFiles(); //here you will get NPE if directory doesn't contains  any file,handle it like this.
                for (File file : files) {
                    if (file.isDirectory()) {
                        if (getPlayList(file.getAbsolutePath()) != null) {
                            fileList.addAll(getPlayList(file.getAbsolutePath()));
                        } else {
                            break;
                        }
                    } else if (file.getName().endsWith(".mp3")) {
                        HashMap<String, String> song = new HashMap<>();
                        song.put("file_path", file.getAbsolutePath());
                        song.put("file_name", file.getName());
                        fileList.add(song);
                        Log.d("SONG",String.valueOf(song));
                    }
                }
                return fileList;
            } catch (Exception e) {
                return null;
            }
        }

}