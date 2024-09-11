package com.tvs.maintenance.fragments;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.tvs.maintenance.R;
import com.tvs.maintenance.util.constants.Constantes;
import com.tvs.maintenance.util.constants.UserEntries.OrdenTrabajoFichaIngreso;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class VehicleIntroDocFragment extends Fragment implements View.OnClickListener {
    private final static int TAG_LEFT = 123, TAG_RIGHT = 234, TAG_FRONT = 345, TAG_BACK = 456;
    ImageView left, right, front, back;
    ImageView ileft, iright, ifront, iback;
    List<Bitmap> imagenes;
    public LinearLayout container;
    private List<CheckBox> filtros;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup con, Bundle savedInstanceState) {
        imagenes = new ArrayList<>();
        imagenes.add(null);
        imagenes.add(null);
        imagenes.add(null);
        imagenes.add(null);
        filtros=new ArrayList<>();
        View root = inflater.inflate(R.layout.fragment_vehicle_intro_form, container, false);
        ImageView img = root.findViewById(R.id.type_picture);
        RadioGroup rg = root.findViewById(R.id.vehicle_info);
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.r2_vehicle) {
                    img.setImageDrawable(getActivity().getDrawable(R.drawable.vechile_2r));
                } else {
                    img.setImageDrawable(getActivity().getDrawable(R.drawable.vehicle_3r));
                }

            }
        });
        left = root.findViewById(R.id.left_picture_btn);
        right = root.findViewById(R.id.right_picture_btn);
        front = root.findViewById(R.id.front_picture_btn);
        back = root.findViewById(R.id.back_picture_btn);
        ileft = root.findViewById(R.id.left_picture);
        iright = root.findViewById(R.id.right_picture);
        ifront = root.findViewById(R.id.front_picture);
        iback = root.findViewById(R.id.back_picture);
        left.setOnClickListener(this);
        right.setOnClickListener(this);
        back.setOnClickListener(this);
        front.setOnClickListener(this);
        container = root.findViewById(R.id.check_container);
        generateChecks();
        return root;
    }

    private void generateChecks() {
        int i = 0;
        while (i < OrdenTrabajoFichaIngreso.opciones.length-1) {
            LinearLayout ll = new LinearLayout(getActivity());
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,100);
            ll.setLayoutParams(params);
            ll.setOrientation(LinearLayout.HORIZONTAL);
            CheckBox cb=new CheckBox(getActivity());
            cb.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1));
            cb.setText(OrdenTrabajoFichaIngreso.opciones[i]);
            cb.setBackgroundTintList(ColorStateList.valueOf(getActivity().getResources().getColor(R.color.colorPrimary)));
            ll.addView(cb);
            filtros.add(cb);
            i++;
            CheckBox cb2=new CheckBox(getActivity());
            cb2.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1));
            cb2.setText(OrdenTrabajoFichaIngreso.opciones[i]);
            cb2.setBackgroundTintList(ColorStateList.valueOf(getActivity().getResources().getColor(R.color.colorPrimary)));
            ll.addView(cb2);
            filtros.add(cb2);
            i++;
            container.addView(ll);
            if (i == OrdenTrabajoFichaIngreso.opciones.length - 2) {
                LinearLayout ll2 = new LinearLayout(getActivity());

                ViewGroup.LayoutParams param = ll2.getLayoutParams();
// Changes the height and width to the specified *pixels*
                param.height = 100;
                param.width = ViewGroup.LayoutParams.MATCH_PARENT;
                ll2.setLayoutParams(param);
                ll2.setOrientation(LinearLayout.HORIZONTAL);
                CheckBox cbt=new CheckBox(getActivity());
                cbt.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1));
                cbt.setText(OrdenTrabajoFichaIngreso.opciones[i]);
                cbt.setBackgroundTintList(ColorStateList.valueOf(getActivity().getResources().getColor(R.color.colorPrimary)));
                filtros.add(cbt);
                ll2.addView(cbt);
                container.addView(ll2);
                i++;
            }
        }
        if (i == OrdenTrabajoFichaIngreso.opciones.length - 1) {
            LinearLayout ll2 = new LinearLayout(getActivity());

            LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,100);
            ll2.setLayoutParams(param);
            ll2.setOrientation(LinearLayout.HORIZONTAL);
            CheckBox cbt=new CheckBox(getActivity());
            cbt.setBackgroundTintList(ColorStateList.valueOf(getActivity().getResources().getColor(R.color.colorPrimary)));
            cbt.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1));
            cbt.setText(OrdenTrabajoFichaIngreso.opciones[i]);
            filtros.add(cbt);
            ll2.addView(cbt);
            container.addView(ll2);
        }
    }

    private void createPdf(String sometext) {
        // create a new document
        PdfDocument document = new PdfDocument();
        // crate a page description
        PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(600, 1200, 1).create();
        // start a page
        PdfDocument.Page page = document.startPage(pageInfo);
        Canvas canvas = page.getCanvas();
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        if (imagenes.get(0) == null) {
            canvas.drawRect(100, 100, 100, 100, paint);
        } else {
            canvas.drawBitmap(imagenes.get(0), 100, 100, paint);
        }
        if (imagenes.get(1) == null) {
            canvas.drawRect(100, 100, 100, 100, paint);
        } else {
            canvas.drawBitmap(imagenes.get(1), 300, 100, paint);
        }
        if (imagenes.get(2) == null) {
            canvas.drawRect(100, 100, 100, 100, paint);
        } else {
            canvas.drawBitmap(imagenes.get(2), 100, 300, paint);
        }
        if (imagenes.get(3) == null) {
            canvas.drawRect(100, 100, 100, 100, paint);
        } else {
            canvas.drawBitmap(imagenes.get(3), 300, 300, paint);
        }
        //canvas.drawt
        // finish the page
        document.finishPage(page);
// draw text on the graphics object of the page
        // Create Page 2
        String directory_path = Environment.getExternalStorageDirectory().getPath() + "/mypdf/";
        File file = new File(directory_path);
        if (!file.exists()) {
            file.mkdirs();
        }
        String targetPdf = directory_path + "OT-" + Constantes.orden + ".pdf";
        File filePath = new File(targetPdf);
        try {
            document.writeTo(new FileOutputStream(filePath));
            Toast.makeText(getContext(), "Done", Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            Log.e("main", "error " + e.toString());
            Toast.makeText(getContext(), "Something wrong: " + e.toString(), Toast.LENGTH_LONG).show();
        }
        // close the document
        document.close();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.left_picture_btn:
                dispatchTakePictureIntent(TAG_LEFT);
                break;
            case R.id.right_picture_btn:
                dispatchTakePictureIntent(TAG_RIGHT);
                break;
            case R.id.back_picture_btn:
                dispatchTakePictureIntent(TAG_BACK);
                break;
            case R.id.front_picture_btn:
                dispatchTakePictureIntent(TAG_FRONT);
                break;
            default:
                break;
        }
    }

    private void dispatchTakePictureIntent(int tag) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, tag);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == getActivity().RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            String _base64 = (getStringBase64(imageBitmap));
            switch (requestCode) {
                case TAG_LEFT:
                    imagenes.set(0, imageBitmap);
                    ileft.setImageBitmap(imageBitmap);
                    break;
                case TAG_RIGHT:
                    imagenes.set(1, imageBitmap);
                    iright.setImageBitmap(imageBitmap);
                    break;
                case TAG_BACK:
                    imagenes.set(2, imageBitmap);
                    iback.setImageBitmap(imageBitmap);
                    break;
                case TAG_FRONT:
                    imagenes.set(3, imageBitmap);
                    ifront.setImageBitmap(imageBitmap);
                    break;
                default:
                    break;
            }
            createPdf("lel");
        }
    }


    public String getStringBase64(Bitmap bm) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(byteArray, Base64.DEFAULT);
    }
}
