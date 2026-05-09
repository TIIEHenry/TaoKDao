package taokdao.api.main.base;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public interface IActivity extends IContext {

    AppCompatActivity getActivity();

    View getContentView();

    void runOnUIThread(@NonNull Runnable runnable);

}
