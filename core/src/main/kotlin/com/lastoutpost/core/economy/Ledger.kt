package com.lastoutpost.core.economy

/**
 * Giao dịch tài nguyên tài khoản. Gameplay KHÔNG tự cộng currency: Mission Result → Reward Calculator →
 * Transaction Builder → Economy Service → Commit → Confirm (Tech Architecture §XXII).
 *
 * @property id định danh duy nhất — dùng làm khoá idempotency.
 * @property deltas resourceId → thay đổi (dương = nhận, âm = chi). resourceId là chuỗi lấy từ dữ liệu
 *   Economy (vd "SCRAP", "BIOMASS"...); core không hard-code danh sách để không tự quyết định canon.
 */
data class Transaction(val id: String, val reason: String, val deltas: Map<String, Long>) {
    init {
        require(id.isNotBlank()) { "Transaction.id không được rỗng" }
        require(deltas.isNotEmpty()) { "Transaction phải có ít nhất một delta" }
    }
}

sealed interface CommitResult {
    /** Đã áp dụng. */
    data object Applied : CommitResult

    /** Cùng id đã được áp dụng trước đó — bỏ qua, không cộng/trừ lần hai. */
    data object Duplicate : CommitResult

    /** Bị từ chối (vd thiếu tài nguyên); số dư giữ nguyên. */
    data class Rejected(val reason: String) : CommitResult
}

/**
 * Sổ cái in-memory: idempotent theo id và nguyên tử (all-or-nothing). Không cho số dư âm.
 * Lưu bền (Room/cloud, versioning, recovery) thuộc milestone Save/Cloud — chưa có ở đây.
 */
class Ledger {
    private val balances = mutableMapOf<String, Long>()
    private val applied = LinkedHashSet<String>()

    fun balance(resourceId: String): Long = balances[resourceId] ?: 0L

    fun hasApplied(transactionId: String): Boolean = transactionId in applied

    fun commit(tx: Transaction): CommitResult {
        if (tx.id in applied) return CommitResult.Duplicate

        for ((resourceId, delta) in tx.deltas) {
            val after = balance(resourceId) + delta
            if (after < 0) {
                return CommitResult.Rejected("Không đủ $resourceId: có ${balance(resourceId)}, cần ${-delta}")
            }
        }
        for ((resourceId, delta) in tx.deltas) {
            balances[resourceId] = balance(resourceId) + delta
        }
        applied += tx.id
        return CommitResult.Applied
    }
}
