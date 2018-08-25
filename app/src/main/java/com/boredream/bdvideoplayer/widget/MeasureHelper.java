package com.boredream.bdvideoplayer.widget;

import android.util.Log;
import android.view.View;
import android.view.View.MeasureSpec;
import java.lang.ref.WeakReference;

/* compiled from: MeasureHelper */
public final class MeasureHelper {
    private WeakReference<View> a;
    private int b;
    private int c;
    private int d;
    private int e;
    private int f;
    private int g;
    private int h;
    private int i = 0;

    public MeasureHelper(View view) {
        this.a = new WeakReference(view);
    }

    public void a(int i, int i2) {
        this.b = i;
        this.c = i2;
    }

    public void b(int i, int i2) {
        this.d = i;
        this.e = i2;
    }

    public void a(int i) {
        this.f = i;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void c(int i, int i2) {
        if (this.f == 90 || this.f == 270) {
            int i3 = i2;
            i2 = i;
            i = i3;
        }
        int defaultSize = View.getDefaultSize(this.b, i);
        int defaultSize2 = View.getDefaultSize(this.c, i2);
        if (this.i != 3) {
            if (this.b > 0 && this.c > 0) {
                defaultSize = MeasureSpec.getMode(i);
                i = MeasureSpec.getSize(i);
                defaultSize2 = MeasureSpec.getMode(i2);
                i2 = MeasureSpec.getSize(i2);
                if (defaultSize == Integer.MIN_VALUE && defaultSize2 == Integer.MIN_VALUE) {
                    float f;
                    float f2 = (float) i;
                    float f3 = (float) i2;
                    float f4 = f2 / f3;
                    switch (this.i) {
                        case 4:
                            f = 1.7777778f;
                            if (this.f == 90 || this.f == 270) {
                                f = 0.5625f;
                                break;
                            }
                        case 5:
                            f = 1.3333334f;
                            if (this.f == 90 || this.f == 270) {
                                f = 0.75f;
                                break;
                            }
                        default:
                            f = ((float) this.b) / ((float) this.c);
                            if (this.d > 0 && this.e > 0) {
                                f = (f * ((float) this.d)) / ((float) this.e);
                                break;
                            }
                    }
                    Object obj = f > f4 ? 1 : null;
                    switch (this.i) {
                        case 0:
                        case 4:
                        case 5:
                            if (obj == null) {
                                i = (int) (f3 * f);
                                break;
                            } else {
                                i2 = (int) (f2 / f);
                                break;
                            }
                        case 1:
                            if (obj == null) {
                                i2 = (int) (f2 / f);
                                break;
                            } else {
                                i = (int) (f3 * f);
                                break;
                            }
                        default:
                            if (obj == null) {
                                i2 = Math.min(this.c, i2);
                                i = (int) (((float) i2) * f);
                                break;
                            }
                            i = Math.min(this.b, i);
                            i2 = (int) (((float) i) / f);
                            break;
                    }
                } else if (defaultSize == 1073741824 && defaultSize2 == 1073741824) {
                    if (this.b * i2 < this.c * i) {
                        i = (this.b * i2) / this.c;
                    } else if (this.b * i2 > this.c * i) {
                        i2 = (this.c * i) / this.b;
                    }
                } else if (defaultSize == 1073741824) {
                    defaultSize = (this.c * i) / this.b;
                    if (defaultSize2 != Integer.MIN_VALUE || defaultSize <= i2) {
                        i2 = defaultSize;
                    }
                } else {
                    int i4;
                    if (defaultSize2 == 1073741824) {
                        i4 = (this.b * i2) / this.c;
                        if (defaultSize == Integer.MIN_VALUE) {
                        }
                    } else {
                        i4 = this.b;
                        int i5 = this.c;
                        if (defaultSize2 != Integer.MIN_VALUE || i5 <= i2) {
                            i2 = i5;
                        } else {
                            i4 = (this.b * i2) / this.c;
                        }
                        if (defaultSize == Integer.MIN_VALUE && i4 > i) {
                            i2 = (this.c * i) / this.b;
                        }
                    }
                    i = i4;
                }
            } else {
                Log.d("MeasureHelper", "no size yet");
                i = defaultSize;
                i2 = defaultSize2;
            }
        }
        this.g = i;
        this.h = i2;
    }

    public int a() {
        return this.g;
    }

    public int b() {
        return this.h;
    }

    public void b(int i) {
        this.i = i;
    }
}
