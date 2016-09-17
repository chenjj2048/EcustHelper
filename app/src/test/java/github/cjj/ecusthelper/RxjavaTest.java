package github.cjj.ecusthelper;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Iterator;

import rx.Observable;
import rx.Subscriber;
import rx.exceptions.OnErrorFailedException;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.observables.GroupedObservable;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Created on 2016/9/17
 *
 * @author chenjj2048
 */
public class RxjavaTest {
    Observable<Integer> o1;
    Observable<Integer> o2;
    Iterator<Integer> mock;

    @Before
    public void init() {
        o1 = Observable.just(1, 2, 3);
        o2 = Observable.just(4, 5, 6);
        mock = Mockito.mock(Iterator.class);
    }

    @Test
    public void testMerge() {
        Mockito.when(mock.next()).thenReturn(1, 2, 3, 4, 5, 6);

        Observable.merge(o1, null, o2)
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        fail();
                    }

                    @Override
                    public void onNext(Integer integer) {
                        System.out.println(integer);
                        assertEquals(integer, mock.next());
                    }
                });
    }

    @Test
    public void testConcat() {
        Mockito.when(mock.next()).thenReturn(1, 2, 3, 4, 5, 6);
        Observable.concat(o1, o2)
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onCompleted() {
                        System.out.println("onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        fail();
                    }

                    @Override
                    public void onNext(Integer integer) {
                        System.out.println(integer);
                        assertEquals(integer, mock.next());
                    }
                });
    }

    @Test(expected = OnErrorFailedException.class)
    public void testConcatNull() {
        Observable.concat(o1, o2, null)
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        fail();
                    }

                    @Override
                    public void onNext(Integer integer) {
                    }
                });
    }
}
