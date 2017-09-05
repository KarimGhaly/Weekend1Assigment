package com.example.admin.navigationapp;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.pdf.PdfRenderer;
import android.os.Build;
import android.os.ParcelFileDescriptor;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class PDFReader extends AppCompatActivity {
    private ImageView img;
    private int currentpage = 0;
   // private Button next, previous;

    private static final String STATE_CURRENT_PAGE_INDEX = "current_page_index";

    private ParcelFileDescriptor mFileDescriptor;

    private PdfRenderer mPdfRenderer;

    private PdfRenderer.Page mCurrentPage;

    private ImageView image;

    private Button previous;

    private Button next;

    private int mPageIndex;
    private ActionBar activity;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdfreader);

        Toolbar toolbar = (Toolbar) findViewById(R.id.nav_action);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        next = (Button) findViewById(R.id.next);
        previous = (Button) findViewById(R.id.previous);
        img = (ImageView) findViewById(R.id.image);
        try {
            mPageIndex = 0;
            openRenderer();
            mCurrentPage = mPdfRenderer.openPage(0);
            showPage(mCurrentPage.getIndex());
            updateUi();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void openRenderer() throws IOException {
        // In this sample, we read a PDF from the assets directory.
        File file = new File(getCacheDir(), "android_tutorial.pdf");
        if (!file.exists()) {
            InputStream asset = getAssets().open("android_tutorial.pdf");
            FileOutputStream output = new FileOutputStream(file);
            final byte[] buffer = new byte[1024];
            int size;
            while ((size = asset.read(buffer)) != -1) {
                output.write(buffer, 0, size);
            }
            asset.close();
            output.close();
        }
        mFileDescriptor = ParcelFileDescriptor.open(file, ParcelFileDescriptor.MODE_READ_ONLY);
        // This is the PdfRenderer we use to render the PDF.
        if (mFileDescriptor != null) {
            mPdfRenderer = new PdfRenderer(mFileDescriptor);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void showPage(int index) {
        if (mPdfRenderer.getPageCount() <= index) {
            return;
        }
        // Make sure to close the current page before opening another one.
        if (null != mCurrentPage) {
            mCurrentPage.close();
        }
        mCurrentPage = mPdfRenderer.openPage(index);
        Bitmap bitmap = Bitmap.createBitmap(mCurrentPage.getWidth(), mCurrentPage.getHeight(),
                Bitmap.Config.ARGB_8888);
        mCurrentPage.render(bitmap, null, null, PdfRenderer.Page.RENDER_MODE_FOR_DISPLAY);
        img.setImageBitmap(bitmap);
        updateUi();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void updateUi() {
        int index = mCurrentPage.getIndex();
        int pageCount = mPdfRenderer.getPageCount();
        previous.setEnabled(0 != index);
        next.setEnabled(index + 1 < pageCount);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public int getPageCount() {
        return mPdfRenderer.getPageCount();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.previous: {
                // Move to the previous page
                showPage(mCurrentPage.getIndex() - 1);
                break;
            }
            case R.id.next: {
                // Move to the next page
                showPage(mCurrentPage.getIndex() + 1);
                break;
            }
        }
    }

}
