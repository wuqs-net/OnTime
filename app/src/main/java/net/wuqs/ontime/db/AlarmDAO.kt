package net.wuqs.ontime.db

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import android.arch.persistence.room.OnConflictStrategy.REPLACE

@Dao
interface AlarmDAO {

    /** All alarms in the database. */
    @get:Query("SELECT * FROM alarms")
    val allLive: LiveData<List<Alarm>>

    /** All alarms in the database. */
    @get:Query("SELECT * FROM alarms")
    val allSync: List<Alarm>

    /** The number of alarms stored in database. */
    @get:Query("SELECT COUNT(*) FROM alarms")
    val count: Int

    /** Returns the [Alarm] of the given id. */
    @Query("SELECT * FROM alarms WHERE id = :id LIMIT 1")
    operator fun get(id: Int): Alarm

    @Query("SELECT * FROM alarms WHERE id = :id LIMIT 1")
    fun getAlarm(id: Int): LiveData<Alarm>

    @Insert(onConflict = REPLACE)
    fun insertAll(vararg alarms: Alarm): LongArray

    @Insert(onConflict = REPLACE)
    fun insert(alarm: Alarm): Long

    @Update
    fun update(alarm: Alarm): Int

    @Update
    fun updateAll(alarms: Collection<Alarm>)

    @Delete
    fun delete(vararg alarms: Alarm): Int
}
