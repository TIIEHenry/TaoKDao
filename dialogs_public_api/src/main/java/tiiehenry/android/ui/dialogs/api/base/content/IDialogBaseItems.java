package tiiehenry.android.ui.dialogs.api.base.content;

import androidx.annotation.ArrayRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.Collection;
import java.util.List;

import tiiehenry.android.ui.dialogs.api.GravityEnum;


public interface IDialogBaseItems<T> {

    /**
     * 设置对话框列表内容的集合
     *
     * @param itemCollection
     * @return
     */
    T items(@NonNull Collection<CharSequence> itemCollection);

    T items(@NonNull List<CharSequence> itemCollection);

    T items(@ArrayRes int itemsRes);

    T items(@NonNull CharSequence... items);


//    T itemsGravity(@NonNull GravityEnum gravity);

    T itemsIds(@NonNull int[] idsArray);

    T itemsIds(@ArrayRes int idsArrayRes);

    /**
     * Sets indices of items that are not clickable. If they are checkboxes or radio buttons, they
     * will not be toggleable.
     *
     * @param disabledIndices The item indices that will be disabled from selection.
     * @return The Builder instance so you can chain calls to it.
     */
    T itemsDisabledIndices(int... disabledIndices);

//
//    /**
//     * 设置对话框列表的点击效果
//     *
//     * @param selectorRes
//     * @return
//     */
//    T listSelector(@DrawableRes int selectorRes);


}
