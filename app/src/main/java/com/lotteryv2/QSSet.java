package com.lotteryv2;

import android.util.Log;

import java.util.ArrayList;
import java.util.Objects;
import java.util.TreeSet;

/**
 * Created by user on 2017/1/3.
 */

public class QSSet {
    private int gameStyle;
    private StringBuffer sb, sbTmp;
    private TreeSet<String> originSet, set, tmpSet,
            dingWeiZhiSet, dingWeiZhiSet2,
            peiShuSet, peiShuSet2,
            buDingWeiHeFenSet, buDingWeiHeFenSet2;

    QSSet(int i) {
        sb = new StringBuffer();
        sbTmp = new StringBuffer();
        originSet = new TreeSet<>();
        dingWeiZhiSet2 = new TreeSet<>();
        peiShuSet2 = new TreeSet<>();
        buDingWeiHeFenSet = new TreeSet<>();
        buDingWeiHeFenSet2 = new TreeSet<>();

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
        initialSet();
    }

    public void initialSet() {
        set = originSet;
    }

    public int getSetSize() {
        return set.size();
    }

    public ArrayList<String> getSet() {
        ArrayList<String> alRes = new ArrayList<>(set);
        return alRes;
    }

    //定位置
    public void dingWeiZhi(boolean chuQu, String qian, String bai, String shi, String ge) {
        dingWeiZhiSet = originSet;
        tmpSet = new TreeSet<>();
        if (!qian.equals("")) quQian(qian);
        if (!bai.equals("")) quBai(bai);
        if (!shi.equals("")) quShi(shi);
        if (!ge.equals("")) quGe(ge);
        for (String s : dingWeiZhiSet) tmpSet.add(s);
        if (!chuQu) {
            dingWeiZhiSet2 = set;
            dingWeiZhiSet2.removeAll(tmpSet);
            if (!qian.equals("")) {
                TreeSet<String> treeSet = new TreeSet<>();
                String a;
                for (String s : originSet) {
                    sbTmp.setLength(0);
                    sbTmp.append(s);
                    a = sbTmp.substring(0, 1);
                    if (a.equals("X")) treeSet.add(s);
                }
                dingWeiZhiSet2.removeAll(treeSet);
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
                dingWeiZhiSet2.removeAll(treeSet);
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
                dingWeiZhiSet2.removeAll(treeSet);
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
                dingWeiZhiSet2.removeAll(treeSet);
            }
        } else {
            dingWeiZhiSet2.addAll(tmpSet);
        }
    }

    public void dingWeiZhi() {
        set = dingWeiZhiSet2;
    }

    //取千
    private void quQian(String et) {
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
    private void quBai(String et) {
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
    private void quShi(String et) {
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
    private void quGe(String et) {
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

    //配數
    public void peiShu(boolean chuQu, String s1, String s2, String s3, String s4) {
        peiShuSet = originSet;
        tmpSet = new TreeSet<>();
        if (!s1.equals("")) quYi(s1);
        if (!s2.equals("")) quEr(s2);
        if (!s3.equals("")) quSan(s3);
        if (!s4.equals("")) quSi(s4);
        for (String s : peiShuSet) tmpSet.add(s);
        if (!chuQu) {
            peiShuSet2 = set;
            peiShuSet2.removeAll(tmpSet);
        } else {
            peiShuSet2.addAll(tmpSet);
        }
    }

    public void peiShu() {
        set = peiShuSet2;
    }

    //取一
    private void quYi(String et) {
        tmpSet = new TreeSet<>();
        String a, b, c, d;
        for (String s : peiShuSet) {
            sbTmp.setLength(0);
            sbTmp.append(s);
            a = sbTmp.substring(0, 1);
            b = sbTmp.substring(1, 2);
            c = sbTmp.substring(2, 3);
            d = sbTmp.substring(3, 4);
            if (a.equals(et) || b.equals(et) || c.equals(et) || d.equals(et)) tmpSet.add(s);
        }
        peiShuSet = tmpSet;
    }

    //取二
    private void quEr(String et) {
        tmpSet = new TreeSet<>();
        String a, b, c, d;
        for (String s : peiShuSet) {
            sbTmp.setLength(0);
            sbTmp.append(s);
            a = sbTmp.substring(0, 1);
            b = sbTmp.substring(1, 2);
            c = sbTmp.substring(2, 3);
            d = sbTmp.substring(3, 4);
            if (a.equals(et) || b.equals(et) || c.equals(et) || d.equals(et)) tmpSet.add(s);
        }
        peiShuSet = tmpSet;
    }

    //取三
    private void quSan(String et) {
        tmpSet = new TreeSet<>();
        String a, b, c, d;
        for (String s : peiShuSet) {
            sbTmp.setLength(0);
            sbTmp.append(s);
            a = sbTmp.substring(0, 1);
            b = sbTmp.substring(1, 2);
            c = sbTmp.substring(2, 3);
            d = sbTmp.substring(3, 4);
            if (a.equals(et) || b.equals(et) || c.equals(et) || d.equals(et)) tmpSet.add(s);
        }
        peiShuSet = tmpSet;
    }

    //取四
    private void quSi(String et) {
        tmpSet = new TreeSet<>();
        String a, b, c, d;
        for (String s : peiShuSet) {
            sbTmp.setLength(0);
            sbTmp.append(s);
            a = sbTmp.substring(0, 1);
            b = sbTmp.substring(1, 2);
            c = sbTmp.substring(2, 3);
            d = sbTmp.substring(3, 4);
            if (a.equals(et) || b.equals(et) || c.equals(et) || d.equals(et)) tmpSet.add(s);
        }
        peiShuSet = tmpSet;
    }

    //合分
    public void heFen1() {

    }

    public void heFen2() {

    }

    public void heFen3() {

    }

    public void heFen4() {

    }

    public void heFen() {

    }

    //不定位合分
    public void buDingWeiHeFen2(String et) {
        tmpSet = new TreeSet<>();
        int a1, b2, c3, d4;
        int i = Integer.parseInt(et);
        String a, b, c, d;
        for (String s : set) {
            sbTmp.setLength(0);
            sbTmp.append(s);
            a = sbTmp.substring(0, 1);
            b = sbTmp.substring(1, 2);
            c = sbTmp.substring(2, 3);
            d = sbTmp.substring(3, 4);
            try {
                a1 = Integer.parseInt(a);
            } catch (NumberFormatException e) {
                a1 = 99;
            }
            try {
                b2 = Integer.parseInt(b);
            } catch (NumberFormatException e) {
                b2 = 99;
            }
            try {
                c3 = Integer.parseInt(c);
            } catch (NumberFormatException e) {
                c3 = 99;
            }
            try {
                d4 = Integer.parseInt(d);
            } catch (NumberFormatException e) {
                d4 = 99;
            }
            if (a1 != 99 && b2 != 99 && (a1 + b2) % 10 == i ||
                    a1 != 99 && c3 != 99 && (a1 + c3) % 10 == i ||
                    a1 != 99 && d4 != 99 && (a1 + d4) % 10 == i ||
                    b2 != 99 && c3 != 99 && (b2 + c3) % 10 == i ||
                    b2 != 99 && d4 != 99 && (b2 + d4) % 10 == i ||
                    c3 != 99 && d4 != 99 && (c3 + d4) % 10 == i) tmpSet.add(s);
        }
        buDingWeiHeFenSet.addAll(tmpSet);
    }

    public void buDingWeiHeFen3(String et) {
        tmpSet = new TreeSet<>();
        int a1, b2, c3, d4;
        int i = Integer.parseInt(et);
        String a, b, c, d;
        for (String s : set) {
            sbTmp.setLength(0);
            sbTmp.append(s);
            a = sbTmp.substring(0, 1);
            b = sbTmp.substring(1, 2);
            c = sbTmp.substring(2, 3);
            d = sbTmp.substring(3, 4);
            try {
                a1 = Integer.parseInt(a);
            } catch (NumberFormatException e) {
                a1 = 99;
            }
            try {
                b2 = Integer.parseInt(b);
            } catch (NumberFormatException e) {
                b2 = 99;
            }
            try {
                c3 = Integer.parseInt(c);
            } catch (NumberFormatException e) {
                c3 = 99;
            }
            try {
                d4 = Integer.parseInt(d);
            } catch (NumberFormatException e) {
                d4 = 99;
            }
            if (a1 != 99 && b2 != 99 && c3 != 99 && (a1 + b2 + c3) % 10 == i ||
                    a1 != 99 && b2 != 99 && d4 != 99 && (a1 + b2 + d4) % 10 == i ||
                    a1 != 99 && c3 != 99 && d4 != 99 && (a1 + c3 + d4) % 10 == i ||
                    b2 != 99 && c3 != 99 && d4 != 99 && (b2 + c3 + d4) % 10 == i) tmpSet.add(s);
        }
        buDingWeiHeFenSet.addAll(tmpSet);
    }

    public void buDingWeiHeFen4(String et) {
        tmpSet = new TreeSet<>();
        int a1, b2, c3, d4;
        int i = Integer.parseInt(et);
        String a, b, c, d;
        for (String s : set) {
            sbTmp.setLength(0);
            sbTmp.append(s);
            a = sbTmp.substring(0, 1);
            b = sbTmp.substring(1, 2);
            c = sbTmp.substring(2, 3);
            d = sbTmp.substring(3, 4);
            a1 = Integer.parseInt(a);
            b2 = Integer.parseInt(b);
            c3 = Integer.parseInt(c);
            d4 = Integer.parseInt(d);
            if ((a1 + b2 + c3 + d4) == i) tmpSet.add(s);
        }
        buDingWeiHeFenSet2.addAll(tmpSet);
    }

    public void buDingWeiHeFen(int i) {
        switch (i) {
            case 0:
                set = buDingWeiHeFenSet;
                break;
            case 1:
                set = buDingWeiHeFenSet2;
                break;
        }
    }

    //全轉

    //上獎

    //排除

    //含

    //定復式

    //雙重
    public void shuangChong(boolean chuQu) {
        tmpSet = new TreeSet<>();
        String a, b, c, d;
        for (String s : set) {
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
            set.removeAll(tmpSet);
        } else {
            set = tmpSet;
        }
    }

    //雙雙重

    //三重

    //四重

    //二兄弟
    public void erXiongDi(boolean chuQu) {
        tmpSet = new TreeSet<>();
        int a1, b2, c3, d4;
        String a, b, c, d;
        for (String s : set) {
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
            set.removeAll(tmpSet);
        } else {
            set = tmpSet;
        }
    }

    //三兄弟

    //四兄弟

    //對數

    //單

    //雙

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

    public void Log(String s) {
        Log.i("troy", s);
    }
}
