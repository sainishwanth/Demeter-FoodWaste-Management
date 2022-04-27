// Generated by view binder compiler. Do not edit!
package com.example.foodwaste.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.foodwaste.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityMainBinding implements ViewBinding {
  @NonNull
  private final RelativeLayout rootView;

  @NonNull
  public final MaterialButton LoginButton;

  @NonNull
  public final FloatingActionButton backtn;

  @NonNull
  public final EditText password;

  @NonNull
  public final TextView signin;

  @NonNull
  public final Button signupbtn;

  @NonNull
  public final EditText username;

  private ActivityMainBinding(@NonNull RelativeLayout rootView, @NonNull MaterialButton LoginButton,
      @NonNull FloatingActionButton backtn, @NonNull EditText password, @NonNull TextView signin,
      @NonNull Button signupbtn, @NonNull EditText username) {
    this.rootView = rootView;
    this.LoginButton = LoginButton;
    this.backtn = backtn;
    this.password = password;
    this.signin = signin;
    this.signupbtn = signupbtn;
    this.username = username;
  }

  @Override
  @NonNull
  public RelativeLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityMainBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityMainBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_main, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityMainBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.LoginButton;
      MaterialButton LoginButton = ViewBindings.findChildViewById(rootView, id);
      if (LoginButton == null) {
        break missingId;
      }

      id = R.id.backtn;
      FloatingActionButton backtn = ViewBindings.findChildViewById(rootView, id);
      if (backtn == null) {
        break missingId;
      }

      id = R.id.password;
      EditText password = ViewBindings.findChildViewById(rootView, id);
      if (password == null) {
        break missingId;
      }

      id = R.id.signin;
      TextView signin = ViewBindings.findChildViewById(rootView, id);
      if (signin == null) {
        break missingId;
      }

      id = R.id.signupbtn;
      Button signupbtn = ViewBindings.findChildViewById(rootView, id);
      if (signupbtn == null) {
        break missingId;
      }

      id = R.id.username;
      EditText username = ViewBindings.findChildViewById(rootView, id);
      if (username == null) {
        break missingId;
      }

      return new ActivityMainBinding((RelativeLayout) rootView, LoginButton, backtn, password,
          signin, signupbtn, username);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
