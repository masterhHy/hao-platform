package com.hao.common.utils;

import Jama.Matrix;
import com.hao.common.utils.vo.Linear;


public class LinearRegression {
    public static void main(String[] args){

        Double rf = 0.0;
        Double[] rm = {1d,2d,3d,4d,5d,6d,7d,8d,9d,10d};//x
        Double[] rp = {2d,4d,6d,8d,10d,12d,14d,16d,18d,20d};//y

        //线性回归
        Linear l = linearRegression(rp,rm,rf);
        System.out.println("线性回归");
        System.out.println("alpha: "+l.alpha+"\nbeta: "+l.beta+"\nr2: "+l.rsquare);

        //自回归
        Autoregressive a = autoRegression(rp,2);
        System.out.println("自回归");
        //参数
        for (double ci :a.ratios){
            System.out.println("ratio: "+ci);
        }
        //拟合值
        for (double ci :a.estimates){
            System.out.println("estimates: "+ci);
        }
        //噪声
        for (double ci :a.noises){
            System.out.println("noises: "+ci);
        }
        //噪声均值
        System.out.println("exp noises: "+exp(a.noises));
        //噪声方差
        System.out.println("dev noises: "+dev(a.noises));

    }

    //求均值
    public  static double exp(double[] rp){
        int len = rp.length;
        if (len > 0){
            double output = 0.0;
            for (double p: rp){
                output +=p;
            }
            output /= len;
            return output;
        }else {
            return -9999;
        }
    }

    //求标准差
    public static double dev(double[] rp){
        int len = rp.length;
        if (len > 0){
            double output = 0.0;
            double exp = exp(rp);
            for (double p: rp){
                output += Math.pow((p -exp),2);
            }
            output = Math.sqrt(output/(len -1));
            return output;
        }else {
            return -9999;
        }
    }

    //求下行风险标准差
    public static double downRisk(double[] rp, double rf){
        int len = rp.length;
        if (len > 0){
            double output = 0.0;
            int count = 0;
            for (double p: rp){
                if (p < rf){
                    count ++;
                    output += Math.pow((p - rf),2);
                }
            }
            if (count > 1){
                output = Math.sqrt(output/(count -1));
                return output;
            }else {
                System.out.println("益率小于无风险利率的天数刚好为1");
                return -9999;
            }
        }else {
            return -9999;
        }
    }

    //求索提诺比率
    public static double sortinoRatio(double exp, double rf, double dr){
        if (dr != 0){
            return (exp - rf)/dr;
        }else {
            System.out.println("下行风险标准差有误");
            return -9999;
        }
    }

    //求夏普比率
    public static double sharpRatio(double exp, double rf, double dp){
        if (dp != 0){
            return (exp - rf)/dp;
        }else {
            System.out.println("标准差为0");
            return -9999;
        }
    }

    //求线性回归 alpha beta R2
    public static Linear linearRegression(Double[] rp,Double[] rm,Double rf){
        Linear output = new Linear(-9999,-9999,-9999);
        int len = rp.length;
        int lenrm = rm.length;
        if (len > 0){
            if (len == lenrm){
                double xexp = 0.0;
                double yexp = 0.0;
                double xsqura = 0.0;
                double ysqura = 0.0;
                double xy = 0.0;
                for (int i = 0; i <len; i++){
                    double yi = rp[i] - rf;
                    double xi = rm[i] - rf;
                    xexp += xi;
                    yexp += yi;
                    xy += xi * yi;
                    xsqura += Math.pow(xi,2);
                    ysqura += Math.pow(yi,2);
                }
                xexp /= len;
                yexp /= len;
                double lxy = xy - len * xexp * yexp;
                double lxx = xsqura - len * Math.pow(xexp,2);
                double lyy = ysqura - len * Math.pow(yexp,2);
                output.beta = lxy / lxx;
                output.alpha = yexp - output.beta * xexp;
                output.rsquare = Math.pow(lxy,2)/(lxx * lyy);
                return output;
            }else {
                System.out.println("市场收益序列长度不匹配");
            }
        }else {
            System.out.println("收益输入为空");
        }
        return output;
    }

    //求詹森系数
    public static double jensen(double eRp, double eRm, double rf, double beta){
        return eRp - rf - beta *(eRm - rf);
    }

    //求特雷诺系数
    public static double treynorRatio(double exp, double rf, double beta){
        if (beta != 0){
            return (exp - rf)/beta;
        }else {
            System.out.println("系统风险beta为0");
            return -9999;
        }
    }

    //自回归分析 求系数 拟合值 噪声序列
   public static Autoregressive autoRegression(Double[] rp, int p){
        double[] op = {-9999};
        Autoregressive output = new Autoregressive(op,op,op);
        int len = rp.length;
        if (len < 3 || p < 1 || len <= p +1){
            System.out.println("输入有误");
        }else {
            int leny = len - p;
            double[][] y = new double[leny][1];
            for (int i = p; i <len; i ++){
                y[i-p][0] = rp[i];
            }
            double[][] a = new double[leny][p];
            for (int i = 0; i < leny ; i ++){
                for (int j = 0 ; j < p; j ++){
                    a[i][j] = rp[p -1 - j + i];
                }
            }
            Matrix mY = new Matrix(y);
            Matrix mA = new Matrix(a);
            Matrix mR = (mA.transpose().times(mA)).inverse().times(mA.transpose()).times(mY);
            output.ratios = mR.getColumnPackedCopy();
            Matrix mYhat = mA.times(mR);
            output.estimates = mYhat.getColumnPackedCopy();
            Matrix mNs = mY.minus(mYhat);
            output.noises = mNs.getColumnPackedCopy();
        }
        return output;
    }
}



class Autoregressive {
    double[] ratios;
    double[] estimates;
    double[] noises;

    public Autoregressive(double[] ratios, double[] estimates, double[] noises){
        this.ratios = ratios;
        this.noises = noises;
        this.estimates = estimates;
    }
}