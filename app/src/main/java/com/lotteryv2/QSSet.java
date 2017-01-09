package com.lotteryv2;

import java.util.TreeSet;

/**
 * Created by user on 2017/1/3.
 */

public class QSSet {
    private int gameStyle;
    private StringBuffer sb, sbRes, sbTmp;
    private TreeSet<String> originSet, tmpSet, dingWeiZhiSet;

    QSSet(int i) {
        sb = new StringBuffer();
        sbRes = new StringBuffer();
        sbTmp = new StringBuffer();
        originSet = new TreeSet<>();

        sb.setLength(4);

        gameStyle = i;

        switch (i) {
            case 1:
                erDingAll();
                break;
            case 2:
                sanDingAll();
                break;
            case 3:
                siDingAll();
                break;
            case 4:
                erXianAll();
                break;
            case 5:
                sanXianAll();
                break;
            case 6:
                siXianAll();
                break;
        }
    }

    public int getSetSize() {
        return originSet.size();
    }

    public String getSet() {
        for (String s : originSet) {
            sbRes.append(s);
            sbRes.append("\n");
        }
        return sbRes.toString();
    }

    //定位置
    public void quDingWeiZhi(boolean chuQu, String qian, String bai, String shi, String ge) {
        dingWeiZhiSet = originSet;
        tmpSet = new TreeSet<>();
        if (!qian.equals("")) QuQian(qian);
        if (!bai.equals("")) QuBai(bai);
        if (!shi.equals("")) QuShi(shi);
        if (!ge.equals("")) QuGe(ge);
        for (String s : dingWeiZhiSet) tmpSet.add(s);
        if (!chuQu) {
            originSet.removeAll(tmpSet);
            if (!qian.equals("")) {
                TreeSet<String> treeSet = new TreeSet<>();
                String a;
                for (String s : originSet) {
                    sbTmp.setLength(0);
                    sbTmp.append(s);
                    a = sbTmp.substring(0, 1);
                    if (a.equals("X")) treeSet.add(s);
                }
                originSet.removeAll(treeSet);
            }
            if (!bai.equals("")) {
                TreeSet<String> treeSet = new TreeSet<>();
                String b;
                for (String s : originSet) {
                    sbTmp.setLength(0);
                    sbTmp.append(s);
                    b = sbTmp.substring(1, 2);
                    if (b.equals("X")) treeSet.add(s);
                }
                originSet.removeAll(treeSet);
            }
            if (!shi.equals("")) {
                TreeSet<String> treeSet = new TreeSet<>();
                String c;
                for (String s : originSet) {
                    sbTmp.setLength(0);
                    sbTmp.append(s);
                    c = sbTmp.substring(2, 3);
                    if (c.equals("X")) treeSet.add(s);
                }
                originSet.removeAll(treeSet);
            }
            if (!ge.equals("")) {
                TreeSet<String> treeSet = new TreeSet<>();
                String d;
                for (String s : originSet) {
                    sbTmp.setLength(0);
                    sbTmp.append(s);
                    d = sbTmp.substring(3, 4);
                    if (d.equals("X")) treeSet.add(s);
                }
                originSet.removeAll(treeSet);
            }
        } else {
            originSet = tmpSet;
        }
    }

    //取千
    private void QuQian(String et) {
        tmpSet = new TreeSet<>();
        String a;
        for (String s : dingWeiZhiSet) {
            sbTmp.setLength(0);
            sbTmp.append(s);
            a = sbTmp.substring(0, 1);
            if (a.equals(et)) tmpSet.add(s);
        }
        dingWeiZhiSet = tmpSet;
    }

    //取百
    private void QuBai(String et) {
        tmpSet = new TreeSet<>();
        String b;
        for (String s : dingWeiZhiSet) {
            sbTmp.setLength(0);
            sbTmp.append(s);
            b = sbTmp.substring(1, 2);
            if (b.equals(et)) tmpSet.add(s);
        }
        dingWeiZhiSet = tmpSet;
    }

    //取十
    private void QuShi(String et) {
        tmpSet = new TreeSet<>();
        String c;
        for (String s : dingWeiZhiSet) {
            sbTmp.setLength(0);
            sbTmp.append(s);
            c = sbTmp.substring(2, 3);
            if (c.equals(et)) tmpSet.add(s);
        }
        dingWeiZhiSet = tmpSet;
    }

    //取個
    private void QuGe(String et) {
        tmpSet = new TreeSet<>();
        String d;
        for (String s : dingWeiZhiSet) {
            sbTmp.setLength(0);
            sbTmp.append(s);
            d = sbTmp.substring(3, 4);
            if (d.equals(et)) tmpSet.add(s);
        }
        dingWeiZhiSet = tmpSet;
    }

    //雙重
    public void shuangChong(boolean chuQu) {
        tmpSet = new TreeSet<>();
        String a, b, c, d;
        for (String s : originSet) {
            sbTmp.setLength(0);
            sbTmp.append(s);
            a = sbTmp.substring(0, 1);
            b = sbTmp.substring(1, 2);
            c = sbTmp.substring(2, 3);
            d = sbTmp.substring(3, 4);
            switch (gameStyle) {
                case 1:
                    if (a.equals(b) && c.equals(d) ||
                            a.equals(c) && b.equals(d) ||
                            a.equals(d) && b.equals(c)) tmpSet.add(s);
                    break;
                case 2:
                    if (a.equals(b) || c.equals(d) ||
                            a.equals(c) || b.equals(d) ||
                            a.equals(d) || b.equals(c)) tmpSet.add(s);
                    break;
                case 3:
                    if (a.equals(b) || c.equals(d) ||
                            a.equals(c) || b.equals(d) ||
                            a.equals(d) || b.equals(c)) tmpSet.add(s);
                    break;
                case 4:
                    if (a.equals(b)) tmpSet.add(s);
                    break;
                case 5:
                    if (a.equals(b) || b.equals(c)) tmpSet.add(s);
                    break;
                case 6:
                    if (a.equals(b) || b.equals(c) || c.equals(d)) tmpSet.add(s);
                    break;
            }
        }
        if (!chuQu) {
            originSet.removeAll(tmpSet);
        } else {
            originSet = tmpSet;
        }
    }

    //二兄弟
    public void erXiongDi(boolean chuQu) {
        tmpSet = new TreeSet<>();
        int a1, b2, c3, d4;
        String a, b, c, d;
        for (String s : originSet) {
            sbTmp.setLength(0);
            sbTmp.append(s);
            a = sbTmp.substring(0, 1);
            b = sbTmp.substring(1, 2);
            c = sbTmp.substring(2, 3);
            d = sbTmp.substring(3, 4);
            try {
                a1 = Integer.parseInt(a);
            } catch (NumberFormatException e) {
                a1 = 10;
            }
            try {
                b2 = Integer.parseInt(b);
            } catch (NumberFormatException e) {
                b2 = 10;
            }
            try {
                c3 = Integer.parseInt(c);
            } catch (NumberFormatException e) {
                c3 = 10;
            }
            try {
                d4 = Integer.parseInt(d);
            } catch (NumberFormatException e) {
                d4 = 10;
            }
            for (int i = 0; i <= 8; i++) {
                if (a1 == i && b2 == i + 1 ||
                        a1 == i && c3 == i + 1 ||
                        a1 == i && d4 == i + 1 ||
                        b2 == i && c3 == i + 1 ||
                        b2 == i && d4 == i + 1 ||
                        c3 == i && d4 == i + 1) {
                    tmpSet.add(s);
                }
            }
            for (int i = 9; i >= 1; i--) {
                if (a1 == i && b2 == i - 1 ||
                        a1 == i && c3 == i - 1 ||
                        a1 == i && d4 == i - 1 ||
                        b2 == i && c3 == i - 1 ||
                        b2 == i && d4 == i - 1 ||
                        c3 == i && d4 == i - 1) {
                    tmpSet.add(s);
                }
            }
            if (a1 == 0 && b2 == 9 ||
                    a1 == 0 && c3 == 9 ||
                    a1 == 0 && d4 == 9 ||
                    b2 == 0 && c3 == 9 ||
                    b2 == 0 && d4 == 9 ||
                    c3 == 0 && d4 == 9) {
                tmpSet.add(s);
            }
            if (a1 == 9 && b2 == 0 ||
                    a1 == 9 && c3 == 0 ||
                    a1 == 9 && d4 == 0 ||
                    b2 == 9 && c3 == 0 ||
                    b2 == 9 && d4 == 0 ||
                    c3 == 9 && d4 == 0) {
                tmpSet.add(s);
            }
        }
        if (!chuQu) {
            originSet.removeAll(tmpSet);
        } else {
            originSet = tmpSet;
        }

    }

    //把數組放進list結構裡面使用
    private void sbToList(String s1, String s2, String s3, String s4) {
        sb.replace(0, 1, s1);
        sb.replace(1, 2, s2);
        sb.replace(2, 3, s3);
        sb.replace(3, 4, s4);

        originSet.add(sb.toString());
    }

    private void sbToList(String s1, String s2) {
        sb.replace(0, 1, s1);
        sb.replace(1, 2, s2);

        originSet.add(sb.toString());
    }

    private void sbToList(String s1, String s2, String s3) {
        sb.replace(0, 1, s1);
        sb.replace(1, 2, s2);
        sb.replace(2, 3, s3);

        originSet.add(sb.toString());
    }

    //把數組從list結構移除
    private void sbRemoveList(String s1, String s2, String s3, String s4) {
        sb.replace(0, 1, s1);
        sb.replace(1, 2, s2);
        sb.replace(2, 3, s3);
        sb.replace(3, 4, s4);

        originSet.remove(sb.toString());
    }

    private void sbRemoveList(String s1, String s2) {
        sb.replace(0, 1, s1);
        sb.replace(1, 2, s2);

        originSet.remove(sb.toString());
    }

    private void sbRemoveList(String s1, String s2, String s3) {
        sb.replace(0, 1, s1);
        sb.replace(1, 2, s2);
        sb.replace(2, 3, s3);

        originSet.remove(sb.toString());
    }

    //二字定全組合
    private void erDingAll() {
        String s1, s2, s3, s4;
        for (int a = 0; a <= 10; a++) {
            for (int b = 0; b <= 10; b++) {
                for (int c = 0; c <= 10; c++) {
                    for (int d = 0; d <= 10; d++) {
                        if (a == 10) {
                            s1 = "X";
                        } else {
                            s1 = String.valueOf(a);
                        }
                        if (b == 10) {
                            s2 = "X";
                        } else {
                            s2 = String.valueOf(b);
                        }
                        if (c == 10) {
                            s3 = "X";
                        } else {
                            s3 = String.valueOf(c);
                        }
                        if (d == 10) {
                            s4 = "X";
                        } else {
                            s4 = String.valueOf(d);
                        }

                        if (s1.equals("X") && s2.equals("X") && s3.equals("X") && s4.equals("X")) {

                        } else if (s2.equals("X") && s3.equals("X") && s4.equals("X")) {

                        } else if (s1.equals("X") && s3.equals("X") && s4.equals("X")) {

                        } else if (s1.equals("X") && s2.equals("X") && s4.equals("X")) {

                        } else if (s1.equals("X") && s2.equals("X") && s3.equals("X")) {

                        } else if (s1.equals("X") && s2.equals("X")) {
                            sbToList(s1, s2, s3, s4);
                        } else if (s1.equals("X") && s3.equals("X")) {
                            sbToList(s1, s2, s3, s4);
                        } else if (s1.equals("X") && s4.equals("X")) {
                            sbToList(s1, s2, s3, s4);
                        } else if (s2.equals("X") && s3.equals("X")) {
                            sbToList(s1, s2, s3, s4);
                        } else if (s2.equals("X") && s4.equals("X")) {
                            sbToList(s1, s2, s3, s4);
                        } else if (s3.equals("X") && s4.equals("X")) {
                            sbToList(s1, s2, s3, s4);
                        } else {

                        }
                    }
                }
            }
        }
    }

    //三字定全組合
    private void sanDingAll() {
        String s1, s2, s3, s4;
        for (int a = 0; a <= 10; a++) {
            for (int b = 0; b <= 10; b++) {
                for (int c = 0; c <= 10; c++) {
                    for (int d = 0; d <= 10; d++) {
                        if (a == 10) {
                            s1 = "X";
                        } else {
                            s1 = String.valueOf(a);
                        }
                        if (b == 10) {
                            s2 = "X";
                        } else {
                            s2 = String.valueOf(b);
                        }
                        if (c == 10) {
                            s3 = "X";
                        } else {
                            s3 = String.valueOf(c);
                        }
                        if (d == 10) {
                            s4 = "X";
                        } else {
                            s4 = String.valueOf(d);
                        }

                        if (s1.equals("X") && s2.equals("X") && s3.equals("X") && s4.equals("X")) {

                        } else if (s2.equals("X") && s3.equals("X") && s4.equals("X")) {

                        } else if (s1.equals("X") && s3.equals("X") && s4.equals("X")) {

                        } else if (s1.equals("X") && s2.equals("X") && s4.equals("X")) {

                        } else if (s1.equals("X") && s2.equals("X") && s3.equals("X")) {

                        } else if (!s1.equals("X") && !s2.equals("X") && !s3.equals("X") && s4.equals("X")) {
                            sbToList(s1, s2, s3, s4);
                        } else if (!s1.equals("X") && !s2.equals("X") && s3.equals("X") && !s4.equals("X")) {
                            sbToList(s1, s2, s3, s4);
                        } else if (!s1.equals("X") && s2.equals("X") && !s3.equals("X") && !s4.equals("X")) {
                            sbToList(s1, s2, s3, s4);
                        } else if (s1.equals("X") && !s2.equals("X") && !s3.equals("X") && !s4.equals("X")) {
                            sbToList(s1, s2, s3, s4);
                        } else {

                        }
                    }
                }
            }
        }
    }

    //四字定全組合
    private void siDingAll() {
        String s1, s2, s3, s4;
        for (int a = 0; a <= 9; a++) {
            for (int b = 0; b <= 9; b++) {
                for (int c = 0; c <= 9; c++) {
                    for (int d = 0; d <= 9; d++) {
                        s1 = String.valueOf(a);
                        s2 = String.valueOf(b);
                        s3 = String.valueOf(c);
                        s4 = String.valueOf(d);
                        sbToList(s1, s2, s3, s4);
                    }
                }
            }
        }
    }

    //二字現全組合
    private void erXianAll() {
        String s1, s2;
        for (int a = 0; a <= 9; a++) {
            for (int b = a; b <= 9; b++) {
                s1 = String.valueOf(a);
                s2 = String.valueOf(b);
                sbToList(s1, s2);
            }
        }
    }

    //三字現全組合
    private void sanXianAll() {
        String s1, s2, s3;
        for (int a = 0; a <= 9; a++) {
            for (int b = a; b <= 9; b++) {
                for (int c = b; c <= 9; c++) {
                    s1 = String.valueOf(a);
                    s2 = String.valueOf(b);
                    s3 = String.valueOf(c);
                    sbToList(s1, s2, s3);
                }
            }
        }
    }

    //四字現全組合
    private void siXianAll() {
        String s1, s2, s3, s4;
        for (int a = 0; a <= 9; a++) {
            for (int b = a; b <= 9; b++) {
                for (int c = b; c <= 9; c++) {
                    for (int d = c; d <= 9; d++) {
                        s1 = String.valueOf(a);
                        s2 = String.valueOf(b);
                        s3 = String.valueOf(c);
                        s4 = String.valueOf(d);
                        sbToList(s1, s2, s3, s4);
                    }
                }
            }
        }
    }
}
