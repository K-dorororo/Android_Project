package com.iot.matzip_project;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Category_activity extends AppCompatActivity {
    private static final int CAPTURE_REQUEST_CODE = 1;
    private static final int GALLERY_REQUEST_CODE = 2;
    private ImageView shareImage1, shareImage2, shareImage3;
    private EventHandler handler = new EventHandler();
    private String[] galleryOrCamera = {"갤러리", "카메라"};
    private Map<Integer, String> imglist = new HashMap<>();
    private Map<Integer, File> fileList = new HashMap<>();
    private Map<Integer, Boolean> checkList = new HashMap<>();
    private Bitmap bmp; // 카메라로 촬영한 이미지를 저장할 변수
    // DB SQLite
    private DBOpenHelper dbOpenHelper;
    public static final String TABLE_NAME = "MatZip";
    SQLiteDatabase database;
    Button submitButton;
    EditText storeEdit;
    EditText commentEdit;
    Spinner category;
    Spinner menu;
    ImageView image1;
    ImageView image2;
    ImageView image3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.write_page);
        submitButton = findViewById(R.id.submit_button);
        storeEdit = findViewById(R.id.storename);
        commentEdit = findViewById(R.id.comment);
        category = findViewById(R.id.spinner_category);
        menu = findViewById(R.id.category_menu);
        image1 = findViewById(R.id.share_image1);
        image2 = findViewById(R.id.share_image2);
        image3 = findViewById(R.id.share_image3);

        dbOpenHelper = DBOpenHelper.getInstance(this);
        database = dbOpenHelper.getWritableDatabase();

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.item_category, // Spinner의 선택 항목 배열 리소스
                android.R.layout.simple_spinner_item
        );
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(
                this,
                R.array.category_menu,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        category.setAdapter(adapter);
        menu.setAdapter(adapter1);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String storeName = storeEdit.getText().toString().trim();
                String comment = commentEdit.getText().toString().trim();
                String sp_category = category.getSelectedItem().toString().trim();
                String menu_category = menu.getSelectedItem().toString().trim();
                insertData(storeName, comment, sp_category, menu_category);
                Intent mainIntent = new Intent(Category_activity.this, MainActivity.class);
                startActivity(mainIntent);
            }
        });

        // 뒤로 가기 버튼 설정
        Button backButton = findViewById(R.id.back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        // 이미지뷰 초기화 및 클릭 리스너 설정
        shareImage1 = findViewById(R.id.share_image1);
        shareImage2 = findViewById(R.id.share_image2);
        shareImage3 = findViewById(R.id.share_image3);

        // 이미지 선택 여부 초기화
        checkList.put(R.id.share_image1, false);
        checkList.put(R.id.share_image2, false);
        checkList.put(R.id.share_image3, false);

        shareImage1.setOnClickListener(handler);
        shareImage2.setOnClickListener(handler);
        shareImage3.setOnClickListener(handler);
    }

    private class EventHandler implements View.OnClickListener {
        Bundle bundle = new Bundle();

        @Override
        public void onClick(View v) {
            if (!checkList.get(v.getId())) { // 이미지 추가
                checkList.put(v.getId(), true);
                new MaterialAlertDialogBuilder(v.getContext())
                        .setTitle("사진 선택")
                        .setItems(galleryOrCamera, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                switch (which) {
                                    case 0: // 갤러리
                                        Intent intent = new Intent(Intent.ACTION_PICK);
                                        bundle.putInt("which", v.getId());
                                        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                                        startActivityForResult(intent, GALLERY_REQUEST_CODE, bundle);
                                        break;
                                    case 1: // 카메라
                                        Intent intent1 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                        bundle.putInt("which", v.getId());
                                        startActivityForResult(intent1, CAPTURE_REQUEST_CODE, bundle);
                                        break;
                                }
                            }
                        }).show();
            } else { // 이미지 삭제
                checkList.put(v.getId(), false);
                imglist.remove(v.getId());
                fileList.remove(v.getId());
                ImageView imageView = null;
                if (v.getId() == R.id.share_image1) {
                    imageView = shareImage1;
                } else if (v.getId() == R.id.share_image2) {
                    imageView = shareImage2;
                } else if (v.getId() == R.id.share_image3) {
                    imageView = shareImage3;
                }
                if (imageView != null) {
                    imageView.setImageResource(R.drawable.insert_image);
                }
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Bundle bundle = data != null ? data.getExtras() : null;

        if (requestCode == CAPTURE_REQUEST_CODE && resultCode == Activity.RESULT_OK && bundle != null) {
            Bitmap bmp = (Bitmap) bundle.get("data");

            // 파일로 저장하기
            String photoImagePath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
                    + File.separator + "image_1.jpg";
            File file = new File(photoImagePath);
            try {
                BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
                bmp.compress(Bitmap.CompressFormat.JPEG, 100, bos);
                bos.flush();
                bos.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

            // 미디어 스캔하여 갤러리에 반영
            Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
            Uri contentUri = Uri.fromFile(file);
            mediaScanIntent.setData(contentUri);
            sendBroadcast(mediaScanIntent);

            // 클릭한 이미지뷰 아이디
            int id = bundle.getInt("which");

            // 파일 리스트에 추가하기
            fileList.put(id, file);

            // 이미지 리스트에 추가하기
            imglist.put(id, file.getName());

            Log.i("com.ami", imglist.toString());

            // 이미지 프리뷰 설정
            if (id == R.id.share_image1) {
                shareImage1.setImageBitmap(bmp);
            } else if (id == R.id.share_image2) {
                shareImage2.setImageBitmap(bmp);
            } else if (id == R.id.share_image3) {
                shareImage3.setImageBitmap(bmp);
            }

        } else if (requestCode == GALLERY_REQUEST_CODE && resultCode == Activity.RESULT_OK && bundle != null) {
            Uri selectedImageUri = data.getData();

            // 이미지 파일 경로 가져오기
            String path = getRealPathFromUri(selectedImageUri);

            if (path != null) {
                File file = new File(path);

                // 클릭한 이미지뷰 아이디
                int id = bundle.getInt("which");

                // 파일 리스트에 추가하기
                fileList.put(id, file);

                // 이미지 리스트에 추가하기
                imglist.put(id, file.getName());

                Log.i("com.ami", imglist.toString());

                // 이미지 프리뷰 설정
                if (id == R.id.share_image1) {
                    shareImage1.setImageURI(selectedImageUri);
                } else if (id == R.id.share_image2) {
                    shareImage2.setImageURI(selectedImageUri);
                } else if (id == R.id.share_image3) {
                    shareImage3.setImageURI(selectedImageUri);
                }
            }
        }
    }


    // Uri에서 실제 파일 경로 가져오기
    private String getRealPathFromUri(Uri uri) {
        String[] filePathColumn = { MediaStore.Images.Media.DATA };
        Cursor cursor = getContentResolver().query(uri, filePathColumn, null, null, null);
        String path = null;
        if (cursor != null) {
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            path = cursor.getString(columnIndex);
            cursor.close();
        }
        return path;
    }

    private void insertData(String storeName, String comment, String sp_category, String menu_category) {
        if(database != null) {
            String sql = " INSERT INTO MatZip(storeName, comment, sp_category, menu_category) VALUES(?, ?, ?, ?)";
            Object[] params = {storeName, comment, sp_category, menu_category};
            database.execSQL(sql, params);
            Toast.makeText(Category_activity.this, "글등록이 완료되었습니다.", Toast.LENGTH_SHORT).show();
            Log.i("test", "Success");
        } else {
            Toast.makeText(Category_activity.this, "저장에 실패했습니다.", Toast.LENGTH_SHORT).show();
        }
    }
}