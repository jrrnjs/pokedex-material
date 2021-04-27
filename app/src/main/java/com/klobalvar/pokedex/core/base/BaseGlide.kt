package com.klobalvar.pokedex.core.base

import android.content.Context
import android.util.Log
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.Priority
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.load.engine.bitmap_recycle.LruBitmapPool
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory
import com.bumptech.glide.load.engine.cache.LruResourceCache
import com.bumptech.glide.load.engine.cache.MemorySizeCalculator
import com.bumptech.glide.module.AppGlideModule
import com.bumptech.glide.request.RequestOptions

@GlideModule
class BaseGlide : AppGlideModule() {

    override fun applyOptions(context: Context, builder: GlideBuilder) {
        val calculator = MemorySizeCalculator.Builder(context).build()

        val bitmapPoolSize = calculator.bitmapPoolSize
        val memoryCacheSize = calculator.memoryCacheSize
        val diskCacheSize = 1024 * 1024 * 50

        val requestOptions = RequestOptions()
            .priority(Priority.NORMAL)

        builder.setBitmapPool(LruBitmapPool(bitmapPoolSize.toLong()))
            .setMemoryCache(LruResourceCache(memoryCacheSize.toLong()))
            .setDiskCache(InternalCacheDiskCacheFactory(context, "pokedex_glide_cache", diskCacheSize.toLong()))
            .setDefaultRequestOptions(requestOptions)
            .setLogLevel(Log.DEBUG)
    }

    override fun isManifestParsingEnabled(): Boolean {
        return false
    }
}