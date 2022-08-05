package com.example.incidencias_sem.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.incidencias_sem.aplication.App
import com.example.incidencias_sem.database.converters.Converters
import com.example.incidencias_sem.database.dao.*
import com.example.incidencias_sem.database.entities.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


@Database(
    entities = arrayOf(
        Users::class,
        Incidence::class,
        Device::class,
        Device_type::class,
        Breakdown::class,
        ConditionIncidence::class,
        ISSI::class,
        Lot::class,
        Contractor::class

    ), version = 1
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun usersDao(): UsersDao
    abstract fun incidenceDao(): IncidenceDao
    abstract fun deviceDao(): DeviceDao
    abstract fun deviceTypeDao(): DeviceTypeDao
    abstract fun breakDownDao(): BreakDownDao
    abstract fun conditionDao(): ConditionDao
    abstract fun issiDao():IssiDao
    abstract fun lotDao():LotDao
    abstract fun contractorDao():ContractorDao


    companion object {

        private var instance: AppDatabase? = null

        private val database = "sem_incidencia"

        fun getDB(context: Context): AppDatabase {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context,
                    AppDatabase::class.java, database
                )
                    .addCallback(callback)
                    .build()
            }
            return instance!!
        }

        private val callback: Callback = object : Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                GlobalScope.launch {
                    App.obtenerDB().deviceTypeDao().saveAll(insertDeviceType())
                    App.obtenerDB().conditionDao().saveAll(insertCondition())
                    App.obtenerDB().breakDownDao().insertAllList(insertBreakdown())
                    App.obtenerDB().deviceDao().saveAll(insertDevice())
                    App.obtenerDB().issiDao().saveAll(insertIssi())
                    App.obtenerDB().lotDao().saveall(insertLot())
                    App.obtenerDB().contractorDao().saveAll(insertContractor())

                }
            }
        }

        private fun insertDeviceType(): List<Device_type> {
            val deviceType = mutableListOf<Device_type>()
            deviceType.add(
                Device_type("STP9000")
            )
            deviceType.add(
                Device_type("SRG3900")
            )

            return deviceType
        }

        private fun insertCondition(): List<ConditionIncidence> {
            val condition = mutableListOf<ConditionIncidence>()
            condition.add(
                ConditionIncidence("En curso")
            )
            condition.add(
                ConditionIncidence("Pendiente")
            )
            condition.add(
                ConditionIncidence("Parada")
            )
            condition.add(
                ConditionIncidence("Cerrada")
            )
            return condition
        }

        private fun insertBreakdown(): List<Breakdown> {
            val breakdown = mutableListOf<Breakdown>()
            breakdown.add(
                Breakdown("Cambio Terminal Subjeccio Bateria")
            )
            breakdown.add(
                Breakdown("Cambio Terminal Potenciometre Volum")
            )
            breakdown.add(
                Breakdown("Cambio Terminal No TX")
            )
            breakdown.add(
                Breakdown("Cambio Terminal Status 7")
            )
            breakdown.add(
                Breakdown("Antena trencada")
            )
            breakdown.add(
                Breakdown("Falsa Incidencia")
            )
            breakdown.add(
                Breakdown("Cambio Altavoz")
            )
            breakdown.add(
                Breakdown("Cambio Micro PTT")
            )
            breakdown.add(
                Breakdown("Cambio Caratula")
            )
            breakdown.add(
                Breakdown("Cambio Radio")
            )
            breakdown.add(
                Breakdown("Cambio Micro ML")
            )
            breakdown.add(
                Breakdown("Cambio Antena GPS/TETRA")
            )
            breakdown.add(
                Breakdown("Cambio Cable GPS")
            )
            breakdown.add(
                Breakdown("Cambio Cable TETRA")
            )
            breakdown.add(
                Breakdown("Cambio Conexionado PC-Embarcat")
            )
            breakdown.add(
                Breakdown("Cambio Varilla TETRA")
            )
            breakdown.add(
                Breakdown("Cambio Conector GPS")
            )
            breakdown.add(
                Breakdown("Cambio Conector TETRA")
            )

            return breakdown
        }

        private fun insertDevice(): List<Device> {
            val device = mutableListOf<Device>()

            device.add(
                Device(
                    "000190013342790",
                    "1PRA01523G8L8U3",
                    "97406DD9680F458504226AF6FCCDD8674000",2,5)
            )
            device.add(
                Device(
                    "000190013374410",
                    "1PRA01525G8M4H9",
                    "F894F3CAE58D088E873CBA8FCDC1EC2F4000",2,5)
            )
            device.add(
                Device(
                    "000190013342160",
                    "1PRA01523G8L8SC",
                    "08D05ABA2CA22E214917990B4E6535DF4000",2,5)
            )
            device.add(
                Device(
                    "000190013376980",
                    "1PRA01525G8M4OE",
                    "10FEC0422FEEA244AAC6953E1066336A4000",2,5)
            )
            device.add(
                Device(
                    "000190013377390",
                    "1PRA01525G8M4PJ",
                    "B40161157E208281432BB8CCABDB500C4000",2,5)
            )
            device.add(
                Device(
                    "000190013342860",
                    "1PRA01523G8L8UA",
                    "BDC5497BF1970CB081D6838345A982EC4000",2,5)
            )
            device.add(
                Device(
                    "000190013384050",
                    "1PRA01525G8M5B4",
                    "983016816230B91459E3A03E32BBABA64000",2,5)
            )
            device.add(
                Device(
                    "000190013388160",
                    "1PRA01525G8M5MJ",
                    "0D2D6E09F3B0F90A4D83AD7D9E7A4CE04000",2,5)
            )
            device.add(
                Device(
                    "000190013344210",
                    "1PRA01523G8L910",
                    "DDC219065DD64AE325FC0E2F71948E2E4000",2,5)
            )
            device.add(
                Device(
                    "000190013344320",
                    "1PRA01523G8L91B",
                    "3FE1B26022650048B0B7606605142A084000",2,5)
            )
            device.add(
                Device(
                    "000190013398540",
                    "1PRA01525G8M6FD",
                    "D20C67D812FEBEE4B64B6FA87CF6208A4000",1,5)
            )
            device.add(
                Device(
                    "000190013398560",
                    "1PRA01525G8M6FF",
                    "2A48EF1B3F7B428950F1840EF66FAA344000",1,5)
            )
            device.add(
                Device(
                    "000190013400360",
                    "1PRA01525G8M6KF",
                    "564BF1F3DB5A0C6AEEF1AEE91E09CD284000",1,5)
            )
            device.add(
                Device(
                    "000190013348380",
                    "1PRA01523G8L9CL",
                    "95A25D94267FEFE8C48D878D828594A64000",1,1)
            )
            device.add(
                Device(
                    "000190013349160",
                    "1PRA01523G8L9WJ",
                    "730D33E03C2766F31A42451C9692481A4000",1,5)
            )
            device.add(
                Device(
                    "000190013407850",
                    "1PRA01525G8M758",
                    "332CF7B883437C73F34DB5E36BDE3E7B4000",1,5)
            )
            device.add(
                Device(
                    "000190013410640",
                    "1PRA01525G8M7CZ",
                    "01333ECE9C07ED8CE51C786B1E1CE7B64000",1,5)
            )
            device.add(
                Device(
                    "000190013366220",
                    "1PRA01524G8M36F",
                    "75668571A0A7DBE7F92CCBB65202CB694000",1,5)
            )
            device.add(
                Device(
                    "000190013416390",
                    "1PRA01526G8M7SY",
                    "A73F9A27C9FA6FE9A880DDCB80D541BC4000",1,5)
            )
            device.add(
                Device(
                    "000190013374270",
                    "1PRA01525G8M4GV",
                    "1BC84D98C6356D69D4F831B976D19E464000",1,5)
            )



            return device
        }
        private fun insertIssi(): List<ISSI> {
            val issi = mutableListOf<ISSI>()

            issi.add(ISSI("4846061"))
            issi.add(ISSI("4817121"))
            issi.add(ISSI("4847321"))
            issi.add(ISSI("4827371"))
            issi.add(ISSI("4827321"))
            issi.add(ISSI("4846001"))
            issi.add(ISSI("4827501"))
            issi.add(ISSI("4827731"))
            issi.add(ISSI("4827401"))
            issi.add(ISSI("4826011"))
            issi.add(ISSI("4806094"))
            issi.add(ISSI("4837434"))
            issi.add(ISSI("4837204"))
            issi.add(ISSI("4837344"))
            issi.add(ISSI("4837614"))
            issi.add(ISSI("4837194"))
            issi.add(ISSI("4856064"))
            issi.add(ISSI("4808204"))
            issi.add(ISSI("4805035"))
            issi.add(ISSI("4806144"))


            return issi
        }

        private fun insertLot(): List<Lot> {
            val lot = mutableListOf<Lot>()
            lot.add(Lot("A"))
            lot.add(Lot("B"))
            lot.add(Lot("C"))
            lot.add(Lot("D"))
            lot.add(Lot("E"))
            lot.add(Lot("F"))
            lot.add(Lot("G"))
            lot.add(Lot("J"))
            lot.add(Lot("Lote 1"))
            lot.add(Lot("Lote 2"))

            return lot
        }
        private fun insertContractor(): List<Contractor> {
            val contractor = mutableListOf<Contractor>()
            contractor.add(Contractor("TSC"))
            contractor.add(Contractor("Egara"))
            contractor.add(Contractor("Sem"))


            return contractor
        }
    }

}

