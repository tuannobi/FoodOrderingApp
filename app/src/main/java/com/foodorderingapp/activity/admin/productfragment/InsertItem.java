package com.foodorderingapp.activity.admin.productfragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.foodorderingapp.R;
import com.foodorderingapp.model.SanPham;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.UUID;

public class InsertItem extends Fragment {
    private static final int PICK_IMAGE_REQUEST = 71;
    private static final int RESULT_OK = -1;
    private String hinhanhid;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    EditText tenSanPham,giaGoc,giaBanLe,moTa;
    RadioGroup radioGroup;
    RadioButton thucuong,thucan;
    Button chonHinhAnh,themSanPham;
    ImageView hinhAnh;
    private Uri url;
    private StorageReference storageReference;



    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.insert_item,container,false);
    }
    public void onViewCreated(@NonNull final View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        AnhXa(view);
        chonHinhAnh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFile();
            }
        });
        themSanPham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // uploadFile();
                final StorageReference ref = storageReference.child("images/"+ UUID.randomUUID().toString());
                ref.putFile(url).continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                    @Override
                    public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                        if (!task.isSuccessful()){
                            throw task.getException();
                        }
                        return ref.getDownloadUrl();
                    }
                }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task) {
                        if (task.isSuccessful()){
                            Uri downUri = task.getResult();
                            Log.d("TAG", "onComplete: Url: "+ downUri.toString());
                            hinhanhid = downUri.toString();
                            String tensanpham = String.valueOf(tenSanPham.getText());
                            float giagoc;
                            float giabanle;
                            float kho;
                            try {
                                giagoc = Float.valueOf(String.valueOf(giaGoc.getText()));
                            } catch (Exception e) {
                                Toast.makeText(getActivity(),e+"Giá trị của giá gốc phải là số",Toast.LENGTH_SHORT).show();
                                return;
                            }
                            try {
                                giabanle = Float.valueOf(String.valueOf(giaBanLe.getText()));
                            } catch (Exception e) {
                                Toast.makeText(getActivity(),e+"Giá trị của giá bán lẻ phải là số",Toast.LENGTH_SHORT).show();
                                return;
                            }

                            String mota = String.valueOf(moTa.getText());



                            int selected = radioGroup.getCheckedRadioButtonId();
                            Toast.makeText(getActivity(),""+selected,Toast.LENGTH_SHORT).show();
                            System.out.println("nè:    " + selected );
                            String phanloaiid = "Thức uống";
                            if(selected!=2131230907){
                                phanloaiid = "Thức ăn";
                            }


                            Toast.makeText(getActivity(),hinhanhid,Toast.LENGTH_SHORT).show();
                            SanPham s1 = new SanPham(null,tensanpham,giagoc,giabanle,"Có",phanloaiid,hinhanhid,mota,0);

                            db.collection("SanPham")
                                    .add(s1)
                                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                        @Override
                                        public void onSuccess(DocumentReference documentReference) {
                                            documentReference.update("sanPhamId",documentReference.getId());
                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Toast.makeText(getActivity(),"Thất bại",Toast.LENGTH_SHORT).show();
                                        }
                                    });


                        }
                    }
                });

















            }
        });

    }
    private void AnhXa(View view){
        storageReference =  FirebaseStorage.getInstance().getReference();
        tenSanPham = (EditText)view.findViewById(R.id.tensanpham1);
        giaGoc = (EditText)view.findViewById(R.id.giagoc1);
        giaBanLe = (EditText)view.findViewById(R.id.giabanle1);
        moTa = (EditText)view.findViewById(R.id.mota1);
        radioGroup = (RadioGroup)view.findViewById(R.id.phanloai);
        chonHinhAnh = (Button)view.findViewById(R.id.chonhinhanh1);
        themSanPham = (Button)view.findViewById(R.id.themsanpham1);
        hinhAnh = (ImageView)view.findViewById(R.id.hinhanh1);

    }
    public void openFile() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Select picture"),PICK_IMAGE_REQUEST);

    }

    public void onDestroy() {
        super.onDestroy();
    }
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==PICK_IMAGE_REQUEST && resultCode==RESULT_OK && data != null && data.getData() != null){
            url = data.getData();
            try {
                Bitmap biMap = MediaStore.Images.Media.getBitmap(getActivity().getApplicationContext().getContentResolver(),url);
                hinhAnh.setImageBitmap(biMap);

            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

