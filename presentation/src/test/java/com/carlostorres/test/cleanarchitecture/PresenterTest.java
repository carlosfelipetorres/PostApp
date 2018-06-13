package com.carlostorres.test.cleanarchitecture;

import android.widget.Button;
import android.widget.TextView;

import com.carlostorres.test.cleanarchitecture.mvp.presenter.PostsPresenter;
import com.carlostorres.test.cleanarchitecture.mvp.view.PostsView;
import com.carlostorres.test.cleanarchitecture.mvp.view.base.MainActivity;
import com.carlostorres.test.domain.GetLatestPostsUseCase;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class PresenterTest {

    private PostsPresenter presenter;
    private MainActivity activity;
    @Mock
    GetLatestPostsUseCase model;
    @Mock
    PostsView view;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        presenter = new PostsPresenter(view, model);
        activity = Robolectric.buildActivity(MainActivity.class)
                .create()
                .resume()
                .get();
    }

    @Test
    public void shouldNotBeNull() throws Exception {
        assertNotNull(activity);
    }

    @Test
    public void showResultOnScreen() throws Exception {
        Button button = activity.findViewById(R.id.btn_call_service);
        TextView results = activity.findViewById(R.id.tv_incoming_json);

        button.performClick();
        assertThat(results.getText().toString()).isNotEmpty();
    }

}