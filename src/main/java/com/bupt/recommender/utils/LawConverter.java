package com.bupt.recommender.utils;

import com.bupt.recommender.entity.LawPO;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LawConverter {
    public static void regularReplace(List<LawPO> lawPOs) {
        lawPOs.forEach(LawConverter::regularReplace);
    }

    public static void regularReplace(LawPO lawPO) {
        lawPO.setIllegalActivities(
                regularReplace(lawPO.getIllegalActivities(), "[$]word[$]topic:", "_______________"));
        lawPO.setIllegalActivities(
                regularReplace(lawPO.getIllegalActivities(), "[$]checkbox:", "_______________"));
        lawPO.setIllegalActivities(
                regularReplace(lawPO.getIllegalActivities(), "[$]topic:", ""));
        lawPO.setIllegalActivities(
                regularReplace(lawPO.getIllegalActivities(), "[$]date", ""));
        lawPO.setIllegalActivities(
                regularReplace(lawPO.getIllegalActivities(), "topic:", ""));

        lawPO.setSuperviseMessage(
                regularReplace(lawPO.getSuperviseMessage(), "[$]word[$]topic:", "_______________"));
        lawPO.setSuperviseMessage(
                regularReplace(lawPO.getSuperviseMessage(), "[$]checkbox:", "_______________"));
        lawPO.setSuperviseMessage(
                regularReplace(lawPO.getSuperviseMessage(), "[$]date", "_______________"));
        lawPO.setSuperviseMessage(
                regularReplace(lawPO.getSuperviseMessage(), "[$]topic:", ""));
        lawPO.setSuperviseMessage(
                regularReplace(lawPO.getSuperviseMessage(), "topic:", ""));
    }

    // https://www.runoob.com/java/java-regular-expressions.html
    public static String regularReplace(String input, String regularExpression, String replace) {
        Pattern p = Pattern.compile(regularExpression);
        Matcher m = p.matcher(input);
        return m.replaceAll(replace);
    }
}
