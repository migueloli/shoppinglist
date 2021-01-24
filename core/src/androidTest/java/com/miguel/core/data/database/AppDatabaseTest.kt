package com.miguel.core.data.database

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.miguel.core.data.dao.ShoppingItemDao
import com.miguel.core.data.dao.ShoppingListDao
import com.miguel.core.data.entity.ShoppingItemEntity
import com.miguel.core.data.entity.ShoppingListEntity
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotSame
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException
import java.util.*

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class AppDatabaseTest {

    private lateinit var db: AppDatabase
    private lateinit var shoppingListDao: ShoppingListDao
    private lateinit var shoppingItemDao: ShoppingItemDao

    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext

        db = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        shoppingListDao = db.shoppingListDao
        shoppingItemDao = db.shoppingItemDao
    }

    @Test
    @Throws(Exception::class)
    fun inserirEBuscarShoppingList() = runBlocking {
        val inserir = ShoppingListEntity(
            id = null,
            descricao = "Teste",
            ativo = true,
            ultimaAlteracao = Calendar.getInstance().timeInMillis,
        )

        val id = shoppingListDao.insert(inserir)

        val inserido = shoppingListDao.selectById(id)

        val list = shoppingListDao.selectAll()

        assertEquals(1, list.size)
        assertNotSame(inserir.id, inserido.id)
        assertEquals(id, inserido.id)
        assertEquals(inserir.descricao, inserido.descricao)
        assertEquals(inserir.ativo, inserido.ativo)
        assertEquals(inserir.ultimaAlteracao, inserido.ultimaAlteracao)
    }

    @Test
    @Throws(Exception::class)
    fun inserirEBuscarShoppingItem() = runBlocking {
        val inserir = ShoppingItemEntity(
            shoppingListId = 1,
            id = null,
            descricao = "Teste",
            quantidade = 1.0,
            valor = 0.0,
            estado = 0,
            ultimaAlteracao = Calendar.getInstance().timeInMillis,
        )

        val id = shoppingItemDao.insert(inserir)

        val inserido = shoppingItemDao.selectById(id)

        val list = shoppingItemDao.selectByShoppingList(inserir.shoppingListId)

        assertEquals(1, list.size)
        assertNotSame(inserir.id, inserido.id)
        assertEquals(id, inserido.id)
        assertEquals(inserir.shoppingListId, inserido.shoppingListId)
        assertEquals(inserir.descricao, inserido.descricao)
        assertEquals(inserir.quantidade, inserido.quantidade)
        assertEquals(inserir.valor, inserido.valor)
        assertEquals(inserir.estado, inserido.estado)
        assertEquals(inserir.ultimaAlteracao, inserido.ultimaAlteracao)
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

}