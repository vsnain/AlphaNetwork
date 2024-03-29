package com.example.alphanetwork.Profile;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.core.widget.TextViewCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.alphanetwork.Model.ModelFeed;
import com.example.alphanetwork.Model.ModelHomeWall;
import com.example.alphanetwork.Model.ModelViewProfile;
import com.example.alphanetwork.Model.ViewProfile;
import com.example.alphanetwork.R;
import com.example.alphanetwork.Retrofit.Api;
import com.example.alphanetwork.Retrofit.RetrofitClient;
import com.example.alphanetwork.addpost.gallery;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import id.zelory.compressor.Compressor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by User on 6/4/2017.
 */

public class EditProfileFragment extends Fragment {


    private static final String TAG = "EditProfileFragment";



    //EditProfile Fragment widgets
    private EditText mDisplayName, mUsername, mPhone, mEmail;
    private TextView mChangeProfilePhoto;
    private CircleImageView mProfilePhoto;
    public static List<String> urls = new ArrayList<>();
    private ViewProfile vp;


    //vars
//    private UserSettings mUserSettings;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_editprofile, container, false);
        mProfilePhoto = (CircleImageView) view.findViewById(R.id.profile_photo);
//        mPhone = view.findViewById(R.id.phoneNumber);
//        mEmail = view.findViewById(R.id.email);
        mUsername = (EditText) view.findViewById(R.id.username);
        mChangeProfilePhoto = (TextView) view.findViewById(R.id.changeProfilePhoto);
        mChangeProfilePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                urls.clear();
                Intent i = new Intent(getActivity(), gallery.class);
                i.putExtra(getString(R.string.calling_activity), getString(R.string.profile_activity));
                getActivity().startActivity(i);

            }
        });


        loadData();

        
        //back arrow for navigating back to "ProfileActivity"
        ImageView backArrow = (ImageView) view.findViewById(R.id.backArrow);
        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: navigating back to ProfileActivity");
                urls.clear();
                getActivity().finish();
            }
        });

        ImageView checkmark = (ImageView) view.findViewById(R.id.saveChanges);
        checkmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: attempting to save changes.");
                try {
                    saveProfileSettings();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        return view;
    }


    @Override
    public void onResume() {
        if (urls.size() != 0) {

//            File file = new File(urls.get(0));
            System.out.println("The url in ON RESUME IS :" );
            Glide.with(getActivity())
                    .load(new File(urls.get(0))) // Uri of the picture
                    .into(mProfilePhoto);

        }
        super.onResume();
    }




    private void loadData() {

        Api api = RetrofitClient.getInstance().getApi();
        Call<ModelViewProfile> call;
        call = api.getProfile();
        call.enqueue(new Callback<ModelViewProfile>() {
            @Override
            public void onResponse(Call<ModelViewProfile> call, Response<ModelViewProfile> response) {
                if(response.isSuccessful() && response.body()!=null){

                    System.out.println(response.body());
                    System.out.println(vp);
                    vp = response.body().getProfile();
                    mUsername.setText(vp.getUsername());




                    Glide.with(getActivity())
                            .load(vp.getPhoto())
                            .placeholder(R.drawable.alphanotext)
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .into(mProfilePhoto);
//        mFollowingCount.setText(viewProfile.getFollowing());



                } else {

                    Toast.makeText(getActivity(), "No Response", Toast.LENGTH_LONG).show();

                }
            }

            @Override
            public void onFailure(Call<ModelViewProfile> call, Throwable t) {
                Toast.makeText(getActivity(), "onFailure is triggered", Toast.LENGTH_LONG).show();
            }

        });



    }


    private void saveProfileSettings() throws IOException {

        final String user = mUsername.getText().toString();



        List<MultipartBody.Part> parts = null;


//pass it like this

        RequestBody username =
                RequestBody.create(MediaType.parse("multipart/form-data"), user);
//        RequestBody phone =
//                RequestBody.create(MediaType.parse("multipart/form-data"), phoneNumber);
//        RequestBody email =
//                RequestBody.create(MediaType.parse("multipart/form-data"), mail);

        if (urls.size() != 0) {
            parts = new ArrayList<>();

            System.out.println("The urls are :" + urls);

            File file = new File(urls.get(0));

//                RequestBody requestFile =
//                        RequestBody.create(MediaType.parse("multipart/form-data"), file);

            RequestBody requestFile =
                    RequestBody.create(MediaType.parse("multipart/form-data"), new Compressor(getActivity()).compressToFile(file));

// MultipartBody.Part is used to send also the actual file name
            MultipartBody.Part body = MultipartBody.Part.createFormData("media", file.getName(), requestFile);
            parts.add(body);
        }

            Call<ResponseBody> call = RetrofitClient
                    .getInstance()
                    .getApi()
                    .updateProfile(parts,username);
            call.enqueue(new Callback<ResponseBody>() {

                @Override
                public void onResponse(Call<ResponseBody> call,
                                       Response<ResponseBody> response) {
                    if(response.isSuccessful()) {
                        Toast.makeText(getActivity(), "Done", Toast.LENGTH_LONG).show();
                    }
                    String m = response.message();
                    System.out.println(m);

                    Log.v("Upload", "success");
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    Log.e("Upload error:", t.getMessage());
                }
            });

        }




    }



