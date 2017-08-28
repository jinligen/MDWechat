package com.blanke.mdwechat.ui;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

import static com.blanke.mdwechat.WeChatHelper.wxConfig;
import static com.blanke.mdwechat.WeChatHelper.xMethod;

/**
 * Created by blanke on 2017/8/1.
 */

public class DiscoverHook extends BaseHookUi {
    //    private String[] hookMenus = {"find_friends_by_near", "find_friends_by_shake",
//            "more_tab_game_recommend", "find_friends_by_qrcode","jd_market_entrance"};
    private String[] hookMenus = {"more_tab_game_recommend", "find_friends_by_qrcode", "jd_market_entrance"};
    private boolean hookListViewBackground = false;

    @Override
    public void hook(XC_LoadPackage.LoadPackageParam lpparam) {
//        xMethod(wxConfig.classes.MMPreferenceAdapter,
//                wxConfig.methods.MMPreferenceAdapter_setVisible,
//                String.class, boolean.class,
//                new XC_MethodHook() {
//                    @Override
//                    protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
//                        String preName = (String) param.args[0];
////                        log("preName=" + preName + ",show=" + param.args[1]);
//                        for (String hookMenu : hookMenus) {
//                            if (hookMenu.equals(preName)) {
//                                param.args[1] = true;
//                                break;
//                            }
//                        }
//                    }
//                });
        xMethod(wxConfig.classes.DiscoverFragment,
                wxConfig.methods.MainFragment_onTabCreate, new XC_MethodHook() {
                    @Override
                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                        View listView = (View) XposedHelpers.getObjectField(param.thisObject,
                                wxConfig.fields.PreferenceFragment_mListView);
                        if (listView != null) {
                            listView.setBackground(new ColorDrawable(Color.WHITE));
                        }
                    }
                });
    }

    private Drawable getBackground() {
        return getColorPrimaryDrawable();
    }
}
