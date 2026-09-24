package com.lastoutpost.core

import com.lastoutpost.core.economy.CommitResult
import com.lastoutpost.core.economy.Ledger
import com.lastoutpost.core.economy.Transaction
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertTrue

class LedgerTest {
    @Test
    fun `giao dich hop le duoc ap dung`() {
        val ledger = Ledger()
        val result = ledger.commit(Transaction("tx-1", "mission reward", mapOf("SCRAP" to 1200L, "BIOMASS" to 60L)))
        assertEquals(CommitResult.Applied, result)
        assertEquals(1200L, ledger.balance("SCRAP"))
        assertEquals(60L, ledger.balance("BIOMASS"))
    }

    @Test
    fun `cung id chi duoc ap dung mot lan - idempotent`() {
        val ledger = Ledger()
        val tx = Transaction("tx-1", "mission reward", mapOf("SCRAP" to 100L))
        assertEquals(CommitResult.Applied, ledger.commit(tx))
        assertEquals(CommitResult.Duplicate, ledger.commit(tx))
        assertEquals(100L, ledger.balance("SCRAP"))
    }

    @Test
    fun `thieu tai nguyen thi tu choi va khong thay doi so du - nguyen tu`() {
        val ledger = Ledger()
        ledger.commit(Transaction("tx-1", "reward", mapOf("SCRAP" to 50L)))
        val result = ledger.commit(Transaction("tx-2", "upgrade", mapOf("SCRAP" to -80L, "BIOMASS" to 10L)))
        assertTrue(result is CommitResult.Rejected)
        assertEquals(50L, ledger.balance("SCRAP"))
        assertEquals(0L, ledger.balance("BIOMASS")) // BIOMASS +10 cũng không được áp dụng
        assertEquals(false, ledger.hasApplied("tx-2"))
    }

    @Test
    fun `giao dich rong hoac id rong bi tu choi`() {
        assertFailsWith<IllegalArgumentException> { Transaction("", "x", mapOf("SCRAP" to 1L)) }
        assertFailsWith<IllegalArgumentException> { Transaction("tx", "x", emptyMap()) }
    }
}
