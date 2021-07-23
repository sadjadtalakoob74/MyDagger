package sadjadtalakoob.ir.mydagger.ui.main.posts;
/*
 * @created 7/23/2021 - 9:57 AM
 * @project MyDagger
 * @author Sadjadt
 */

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;
import sadjadtalakoob.ir.mydagger.R;
import sadjadtalakoob.ir.mydagger.models.Post;
import sadjadtalakoob.ir.mydagger.ui.main.Resource;
import sadjadtalakoob.ir.mydagger.util.VerticalSpaceItemDecoration;
import sadjadtalakoob.ir.mydagger.viewmodels.ViewModelProviderFactory;

public class PostFragment extends DaggerFragment {
    private static final String TAG = "PostFragment";
    private PostsViewModel postsViewModel;
    private RecyclerView recyclerView;

    @Inject
    PostRecyclerAdapter postRecyclerAdapter;

    @Inject
    ViewModelProviderFactory viewModelProviderFactory;

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_posts, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        recyclerView = view.findViewById(R.id.recycler_view);
        postsViewModel = ViewModelProviders.of(this, viewModelProviderFactory).get(PostsViewModel.class);

        initRecyclerView();
        subscribeObservers();
    }

    private void subscribeObservers() {
        postsViewModel.observePosts().removeObservers(getViewLifecycleOwner());
        postsViewModel.observePosts().observe(getViewLifecycleOwner(), new Observer<Resource<List<Post>>>() {
            @Override
            public void onChanged(Resource<List<Post>> listResource) {
                if (listResource != null) {
                    switch (listResource.status) {
                        case SUCCESS:
                            Log.d(TAG, "onChanged: got posts....");
                            postRecyclerAdapter.setPosts(listResource.data);
                            break;

                        case LOADING:
                            Log.d(TAG, "onChanged: Loding...");
                            break;
                        case ERROR:
                            Log.e(TAG, "onChanged: Error...+" + listResource.message);
                            break;
                    }
                }
            }
        });
    }

    private void initRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        VerticalSpaceItemDecoration verticalSpaceItemDecoration = new VerticalSpaceItemDecoration(15);
        recyclerView.addItemDecoration(verticalSpaceItemDecoration);
        recyclerView.setAdapter(postRecyclerAdapter);
    }
}
