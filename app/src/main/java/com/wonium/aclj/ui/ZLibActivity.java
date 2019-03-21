package com.wonium.aclj.ui;

import android.Manifest;
import android.graphics.Bitmap;
import android.os.Environment;
import android.view.View;

import com.wonium.aclj.R;
import com.wonium.aclj.databinding.ActivityZlibBinding;
import com.wonium.cicada.utils.ByteUtil;
import com.wonium.cicada.utils.FileUtil;
import com.wonium.cicada.utils.ToastUtil;
import com.wonium.cicada.utils.ZLibUtil;

import java.io.File;
import java.io.FileOutputStream;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import ru.alexbykov.nopermission.PermissionHelper;

public class ZLibActivity extends BaseActivity {
    private ActivityZlibBinding mBinding;
    private byte[] src;
    private byte[] compressSrc;
    private String result;
    private PermissionHelper  helper;
    @Override
    public void initWindowAttributes() {
        setAllowFullScreen(false);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.activity_zlib;
    }

    @Override
    public void bindLayout(int layoutResId) {
        mBinding= DataBindingUtil.setContentView(this,layoutResId);
    }

    @Override
    public void initView() {
         helper =new PermissionHelper(this);
        helper.check(Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE)
                .onSuccess(this::onSuccess)
                .onDenied(this::onDenied)
                .onNeverAskAgain(this::onNeverAskAgain).run();
    }

    @Override
    public void initListener() {
        mBinding.btnCompress.setOnClickListener(view -> {
        src =mBinding.tvSrc.getText().toString().getBytes();
        compressSrc = ZLibUtil.getInstance().compress(src);

        new Thread(() -> {
            String path = Environment.getExternalStorageDirectory() +FileUtil.INSTANCE.generateFilePath("test","compress",".zhd");

            File file = new File(path);
            if (file.exists()) {
                file.delete();
            }
            FileUtil.INSTANCE.writeFile(path,compressSrc);
        }).start();

        System.out.println(ByteUtil.INSTANCE.printHexBinary(compressSrc));
        mBinding.tvCompress.setText(new String(compressSrc));
        });
        mBinding.btnUnCompress.setOnClickListener(view -> mBinding.tvResult.setText(new String(ZLibUtil.getInstance().decompress(compressSrc))));
    }

    public void onSuccess() {
        ToastUtil.INSTANCE.show(getContext(), "授权成功");
    }

    public void onDenied() {
        ToastUtil.INSTANCE.show(getContext(), "权限拒绝");
    }

    public void onNeverAskAgain() {
        ToastUtil.INSTANCE.show(getContext(), "权限拒绝，并且不再询问");
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults){
        helper.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
