package com.qiyi.mvptest.rxjava.test;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.qiyi.mvptest.R;

import java.util.List;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by kezhan_sx on 2016/7/5.
 */
public class RxJavaTest extends Activity {

    private static final String TAG = "RxJavaTest";

    public void test() {
        Observer<String> observer = new Observer<String>() {
            @Override
            public void onCompleted() {
                Log.e(TAG, "onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError");
            }

            @Override
            public void onNext(String s) {
                Log.e(TAG, "onNext");
            }
        };

        final Subscriber<String> subscriber = new Subscriber<String>() {
            @Override
            public void onStart() {
                super.onStart();
            }

            @Override
            public void onCompleted() {
                Log.e(TAG, "onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError");
            }

            @Override
            public void onNext(String s) {
                Log.e(TAG, "onNext");
            }

        };

        Observable observable = Observable.create(new Observable.OnSubscribe() {
            @Override
            public void call(Object o) {
                subscriber.onNext("hi");
                subscriber.onNext("hello");
                subscriber.onNext("world");
                subscriber.onCompleted();
            }
        });

        Observable observable1 = Observable.just("hi", "hello", "world");

        String[] words = {"hi", "hello", "world"};
        Observable observable2 = Observable.from(words);

        observable.subscribe(observer);
        observable.subscribe(subscriber);

        Action1<String> onNextAction = new Action1<String>() {
            @Override
            public void call(String s) {
                Log.e(TAG, s);
            }
        };

        Action1<Throwable> onErrorAction = new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                Log.e(TAG, "onErrorAction");
            }
        };

        Action0 onCompletedAction = new Action0() {
            @Override
            public void call() {
                Log.e(TAG, "onCompletedAction");
            }
        };

        observable.subscribe(onNextAction);
        observable.subscribe(onNextAction, onErrorAction);
        observable.subscribe(onNextAction, onErrorAction, onCompletedAction);

        String[] names = {"1", "2", "3", "4"};
        Observable.from(names)
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        Log.e(TAG, s);
                    }
                });

        final int drawableRes = R.drawable.buy;
        final ImageView imageView = null;
        Observable.create(new Observable.OnSubscribe<Drawable>() {
            @Override
            public void call(Subscriber<? super Drawable> subscriber) {
                Drawable drawable = getTheme().getDrawable(drawableRes);
                subscriber.onNext(drawable);
                subscriber.onCompleted();
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Drawable>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onNext(Drawable drawable) {
                        imageView.setImageDrawable(drawable);
                    }
                });

        Observable.just(1, 2, 3, 4)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        Log.e(TAG, "num:" + integer);
                    }
                });

        Observable.just("images/logo.png")
                .map(new Func1<String, Bitmap>() {
                    @Override
                    public Bitmap call(String s) {
                        return null;
                    }
                })
                .subscribe(new Action1<Bitmap>() {
                    @Override
                    public void call(Bitmap bitmap) {
                        //showBitmap(bitmap);
                    }
                });

        Student[] students = null;
        Observable.from(students).flatMap(new Func1<Student, Observable<Course>>() {
            @Override
            public Observable<Course> call(Student student) {
                return Observable.from(student.getCourse());
            }
        }).subscribe(new Subscriber<Course>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Course course) {
                Log.e(TAG, course.name);
            }
        });

        Observable.from(students).map(new Func1<Student, String>() {
            @Override
            public String call(Student student) {
                return student.name;
            }
        }).subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                Log.e(TAG, s);
            }
        });

        Observable.from(students).map(new Func1<Student, List<Course>>() {
            @Override
            public List<Course> call(Student student) {
                return student.getCourse();
            }
        }).subscribe(new Subscriber<List<Course>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(List<Course> courses) {
                for(Course c : courses) {
                    Log.e(TAG, c.name);
                }
            }
        });

        Observable.Transformer liftAll = new LiftAlltransformer();
        observable.compose(liftAll).subscribe(subscriber);

        observable.just(1, 2, 3, 4)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.newThread())
                .map(new Func1<Integer, String>() {
                    @Override
                    public String call(Integer integer) {
                        return null;
                    }
                })
                .observeOn(Schedulers.io())
                .map(new Func1<String, Drawable>() {
                    @Override
                    public Drawable call(String s) {
                        return null;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Drawable>() {
                    @Override
                    public void call(Drawable drawable) {

                    }
                });

    }


    class LiftAlltransformer implements Observable.Transformer<Integer, String> {

        @Override
        public Observable<String> call(Observable<Integer> observable) {
            return observable.flatMap(new Func1<Integer, Observable<Drawable>>() {
                @Override
                public Observable<Drawable> call(Integer integer) {
                    return null;
                }
            }).flatMap(new Func1<Drawable, Observable<String>>() {
                @Override
                public Observable<String> call(Drawable drawable) {
                    return null;
                }
            });
        }
    }
}
