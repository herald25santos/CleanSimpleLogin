package herald.company.domain.common

abstract class MapperFromCallable<in T,E>{

    abstract fun mapFrom(from: T): E

    fun flowable(from: T) = io.reactivex.Flowable.fromCallable { mapFrom(from) }

    fun flowable(from: List<T>) = io.reactivex.Flowable.fromCallable { from.map { mapFrom(it) } }

    fun single(from: T) = io.reactivex.Single.fromCallable { mapFrom(from) }

    fun single(from: List<T>) = io.reactivex.Single.fromCallable { from.map { mapFrom(it) } }
}