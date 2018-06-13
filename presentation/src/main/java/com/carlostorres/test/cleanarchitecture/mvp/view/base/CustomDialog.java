package com.carlostorres.test.cleanarchitecture.mvp.view.base;


import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.carlostorres.test.cleanarchitecture.R;
import com.carlostorres.test.data.PostsServicesImpl;
import com.carlostorres.test.domain.GetSinglePostUseCase;

import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;

public class CustomDialog extends DialogFragment {

    int mId;
    ImageView iv;
    static CustomDialog newInstance(int id) {
        CustomDialog f = new CustomDialog();

        // Supply num input as an argument.
        Bundle args = new Bundle();
        args.putInt("id", id);
        f.setArguments(args);

        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mId = getArguments().getInt("id");
        GetSinglePostUseCase getSinglePostUseCase = new GetSinglePostUseCase(new PostsServicesImpl());
        getSinglePostUseCase.execute(new DisposableObserver<Object>() {
            @Override
            public void onNext(@NonNull Object imageUrl) {
                Glide.with(getContext()).load(imageUrl).into(iv);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                e.toString();
            }

            @Override
            public void onComplete() {
            }
        },mId);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_dialog, container, false);
        View tv = v.findViewById(R.id.tv_id_image);
        ((TextView)tv).setText(String.valueOf(mId));

        iv = v.findViewById(R.id.iv_full_image);


        return v;
    }
}

