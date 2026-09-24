# ADR-0001: Android native + `:core` Kotlin/JVM thuần

- **Status:** Proposed — **giả định**, cần xác nhận (OQ-TECH-01)
- **Ngày:** 2026-09-24
- **Liên quan:** Technical Architecture v1.0 §I, §II, §XXXIV.1; OQ-TECH-01

## Bối cảnh
Technical Architecture v1.0 **không chọn engine**: chỉ đặt ranh giới module (Presentation / Application / Gameplay Runtime / Core Services)
và nguyên tắc *"gameplay simulation không phụ thuộc UI"*. Bundle yêu cầu "Claude triển khai" nhưng không nêu nền tảng.
Mục tiêu hiệu năng của thiết kế: 1000+ enemy instance, 60 FPS (fallback 30), fixed tick 20–30/s.

## Quyết định
1. Client là **app Android native (Kotlin + Jetpack Compose)** — trùng stack của đội (Kotlin, Compose, GitHub Actions).
2. Mô phỏng nằm trong module **`:core` — Kotlin/JVM thuần**, không import Android/Compose/engine.
   `:app` chỉ là Presentation + Application shell.
3. **Renderer 2.5D của combat CHƯA được chọn.** Compose Canvas có thể không đủ cho 1000+ entity; ứng viên: OpenGL ES/Vulkan tối giản,
   libGDX (Kotlin), hoặc engine ngoài. Quyết định này phải có **trước** milestone "Basic Renderer" (OQ-TECH-01).

## Hệ quả
- (+) Test mô phỏng trên CI bằng JVM thường, không emulator; replay/deterministic-friendly (Command queue + seed).
- (+) Đổi renderer/engine **không** phải viết lại luật game: chỉ thay lớp Presentation.
- (−) Nếu chọn engine không dùng JVM (Unity, Godot), `:core` phải được port — chi phí có thật.
- (−) Chưa có iOS; thiết kế cũng chưa yêu cầu.

## Cách rollback
`:core` không phụ thuộc gì ngoài stdlib → có thể tách thành thư viện độc lập hoặc port; `:app` thay được mà không đụng `:core`.
