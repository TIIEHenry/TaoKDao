package tiiehenry.taokdao.ui.view.tablayout;

/**
 * Callback interface invoked when a tab's selection state changes.
 */
public interface OnTabSelectedListener {
    /**
     * Called when a tab enters the selected state.
     *
     * @param tab The tab that was selected
     */
    void onTabSelected(TabLayout.Tab tab);

    /**
     * Called when a tab exits the selected state.
     *
     * @param tab The tab that was unselected
     */
    void onTabUnselected(TabLayout.Tab tab);

    /**
     * Called when a tab that is already selected is chosen again by the user. Some applications may
     * use this action to return to the top level of a category.
     *
     * @param tab The tab that was reselected.
     */
    void onTabReselected(TabLayout.Tab tab);
}
