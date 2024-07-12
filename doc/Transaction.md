### TRANSACTIONAL

## Spring Data JPA

# Annotation Interface Transactional
### Package org.springframework.transaction.annotation

### Konsep dasar Transaction

ACID Properties:

- Atomicity: Seluruh operasi dalam transaksi dijalankan secara penuh atau tidak sama sekali. Jika salah satu operasi gagal, maka seluruh transaksi dibatalkan (rollback).
- Consistency: Transaksi harus membawa database dari satu keadaan yang konsisten ke keadaan konsisten lainnya.
- Isolation: Transaksi dijalankan secara independen, sehingga hasil dari transaksi yang sedang berlangsung tidak terlihat oleh transaksi lain sampai transaksi tersebut selesai.
- Durability: Hasil dari transaksi yang berhasil dijalankan harus disimpan secara permanen dalam basis data, bahkan jika terjadi kegagalan sistem setelah transaksi selesai.
  
Commit dan Rollback:

- Commit: Mengakhiri transaksi dengan menyimpan semua perubahan yang telah dibuat. Setelah commit, perubahan menjadi permanen.
- Rollback: Membatalkan transaksi dengan mengembalikan basis data ke keadaan sebelum transaksi dimulai.

Penggunaan Transaksi:

- Dalam SQL: Transaksi biasanya dimulai dengan perintah BEGIN TRANSACTION, diakhiri dengan COMMIT untuk menyimpan perubahan atau ROLLBACK untuk membatalkan perubahan.
- Dalam Pemrograman: Berbagai bahasa pemrograman dan framework menyediakan dukungan untuk transaksi. Misalnya, dalam Java dengan Spring Framework, transaksi dapat dikelola dengan anotasi @Transactional.
- Saat kita menggunakan JPA secara manual, kita harus melakukan management transaction secara manual menggunakan EntityManager
- Spring menyediakan fitur Declarative Transaction, yaitu management transaction secara declarative, yaitu dengan menggunakan annotation @Transactional
- Annotation ini secara otomatis dibaca oleh Spring AOP, dan akan menjalankan transaction secara otomatis ketika memanggil method yang terdapat annotation @Transactional nya

### https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/transaction/annotation/Transactional.html 

# Property dalam anotasi @Transaction

# Isolation 

(The transaction isolation level.)

### Contoh Penggunaan

```
// || DEFAULT || READ_UNCOMMITTED || READ_COMMITTED, REPEATABLE_READ ||  SERIALIZABLE

@Transactional(isolation = Isolation.REPEATABLE_READ) 
public void processPayment(TransactionRequest request) {
    // Proses pembayaran
}
```

## Skenario

Kita akan menggunakan dua transaksi (Transaction A dan Transaction B) untuk menguji perilaku berbagai level isolasi.
Pengaturan Awal:

Di dalam database, terdapat tabel Product dengan satu baris data:
- id: 1
- name: "Product A"
- quantity: 100

### Isolation Level DEFAULT

Bergantung pada konfigurasi default dari database yang digunakan.

### Isolation Level: READ_UNCOMMITTED

Deskripsi:

Level isolasi ini memungkinkan transaksi membaca data yang belum di-commit oleh transaksi lain. Hal ini dapat menyebabkan dirty reads.

Skenario:

- Transaction A memulai dan membaca quantity dari Product dengan id=1.
- Transaction B memulai dan mengubah quantity dari Product dengan id=1 menjadi 120 tetapi belum di-commit.
- Transaction A membaca kembali quantity dari Product dengan id=1 dan melihat nilai 120 (dirty read).
- Transaction B melakukan rollback sehingga perubahan tidak di-commit.

Hasil:

Transaction A melihat nilai quantity yang belum di-commit oleh Transaction B.

### Isolation Level: READ_COMMITTED

Deskripsi:

Level isolasi ini mencegah dirty reads. Transaksi hanya dapat melihat data yang telah di-commit oleh transaksi lain.

Skenario:

- Transaction A memulai dan membaca quantity dari Product dengan id=1.
- Transaction B memulai dan mengubah quantity dari Product dengan id=1 menjadi 120 tetapi belum di-commit.
- Transaction A membaca kembali quantity dari Product dengan id=1 dan melihat nilai 100 (tidak melihat perubahan yang belum di-commit).
- Transaction B melakukan commit.
- Transaction A membaca kembali quantity dan melihat nilai 120.

Hasil:

Transaction A hanya melihat perubahan setelah Transaction B melakukan commit.

### Isolation Level: REPEATABLE_READ

Deskripsi:

Level isolasi ini mencegah dirty reads dan non-repeatable reads. Transaksi akan melihat data yang konsisten pada setiap baca selama transaksi berlangsung.

Skenario:

- Transaction A memulai dan membaca quantity dari Product dengan id=1.
- Transaction B memulai dan mengubah quantity dari Product dengan id=1 menjadi 120 tetapi belum di-commit.
- Transaction A membaca kembali quantity dari Product dengan id=1 dan masih melihat nilai 100.
- Transaction B melakukan commit.
- Transaction A membaca kembali quantity dan tetap melihat nilai 100 hingga transaksi selesai.

Hasil:

- Transaction A melihat data yang sama di setiap baca meskipun 
- Transaction B telah melakukan commit.

### Isolation Level: SERIALIZABLE

Deskripsi:

Level isolasi ini adalah yang tertinggi dan mencegah dirty reads, non-repeatable reads, dan phantom reads. Transaksi dieksekusi seolah-olah secara serial.

Skenario:

- Transaction A memulai dan membaca quantity dari Product dengan id=1.
- Transaction B memulai dan mencoba mengubah quantity dari Product dengan id=1 menjadi 120 tetapi harus menunggu hingga Transaction A selesai.
- Transaction A membaca kembali quantity dari Product dengan id=1 dan melihat nilai 100.
- Transaction A selesai dan melakukan commit.
- Transaction B sekarang dapat melanjutkan dan melakukan perubahan.

Hasil:

- Transaction B harus menunggu hingga Transaction A selesai sebelum melakukan perubahan.

Kesimpulan

- READ_UNCOMMITTED: Memungkinkan dirty reads, memberikan performa tertinggi tetapi konsistensi data terendah.
- READ_COMMITTED: Mencegah dirty reads, cukup sering digunakan karena kompromi antara konsistensi dan performa.
- REPEATABLE_READ: Mencegah dirty reads dan non-repeatable reads, memberikan konsistensi yang lebih tinggi dengan sedikit penurunan performa.
- SERIALIZABLE: Mencegah dirty reads, non-repeatable reads, dan phantom reads, memberikan konsistensi tertinggi dengan performa terendah.

# Label 

- Identifikasi Transaksi: Label digunakan untuk mengidentifikasi atau memberi nama pada transaksi tertentu. Ini memungkinkan kita untuk membedakan satu transaksi dengan transaksi lainnya dalam kasus di mana kita memiliki banyak transaksi dengan konfigurasi yang mirip.

- Seleksi Transaksi: Dalam beberapa kasus, kita mungkin memiliki beberapa definisi transaksi yang berbeda untuk berbagai keperluan dalam aplikasi kita. Dengan menggunakan label, kita dapat memilih transaksi yang tepat untuk digunakan dalam kasus tertentu.

- Konfigurasi Lebih Lanjut: Label sering digunakan bersama dengan anotasi atau konfigurasi lainnya untuk transaksi, seperti timeout, jenis propagasi, tingkat isolasi, dan aturan rollback. Ini memungkinkan kita untuk secara fleksibel mengatur perilaku transaksi yang berbeda berdasarkan kebutuhan bisnis kita.

### Contoh Penggunaan

```
@Transactional("transactionManager")
public void processPayment(TransactionRequest request) {
    // Proses pembayaran
}
```

# noRollbackFor 

noRollbackFor mendefinisikan satu atau lebih jenis exception yang, ketika dilemparkan, tidak akan menyebabkan rollback transaksi. Exception ini harus merupakan subclass dari Throwable.

Kadang-kadang kita mungkin ingin menangani beberapa exception tanpa membatalkan semua perubahan dalam transaksi. noRollbackFor memungkinkan kita untuk menentukan exception-exception yang tidak perlu menyebabkan rollback, sehingga beberapa perubahan dapat tetap dipertahankan meskipun terjadi exception tertentu.

noRollbackFor biasanya digunakan ketika kita ingin melanjutkan transaksi atau menangani exception secara khusus tanpa kehilangan semua pekerjaan yang sudah dilakukan dalam transaksi tersebut.

### Contoh Penggunaan

```
@Service
public class MyService {

    @Transactional(noRollbackFor = CustomException.class)
    public void performTransaction() {
        // Kode transaksi

        // Exception yang tidak menyebabkan rollback
        if (someCondition) {
            throw new CustomException("Custom exception occurred");
        }

        // Kode transaksi lebih lanjut
    }
}
```

# noRollbackForClassName

noRollbackForClassName adalah elemen opsional dalam anotasi @Transactional di Spring Framework yang menentukan pola nama exception yang tidak akan menyebabkan rollback transaksi. Ini mirip dengan noRollbackFor, tetapi menggunakan nama class exception dalam bentuk string, memungkinkan kita untuk menentukan exception berdasarkan nama mereka.

noRollbackForClassName mendefinisikan satu atau lebih nama class exception (dalam bentuk string) yang tidak akan menyebabkan rollback transaksi ketika exception tersebut dilemparkan. Nama exception harus merupakan subclass dari Throwable.

Kadang-kadang, kita mungkin ingin menangani beberapa exception tanpa membatalkan semua perubahan dalam transaksi, terutama jika exception tersebut hanya mewakili kondisi kesalahan tertentu yang tidak memerlukan rollback. noRollbackForClassName memungkinkan kita untuk menentukan exception-exception tersebut berdasarkan nama mereka.

noRollbackForClassName biasanya digunakan ketika kita ingin menggunakan nama class exception sebagai string, yang bisa berguna jika kita ingin menentukan exception yang mungkin tidak ada dalam classpath saat waktu kompilasi atau jika kita ingin menghindari ketergantungan langsung pada class exception tertentu.

### Contoh Penggunaan

```
@Service
public class MyService {

    @Transactional(noRollbackForClassName = "com.example.CustomException")
    public void performTransaction() {
        // Kode transaksi

        // Exception yang tidak menyebabkan rollback
        if (someCondition) {
            throw new CustomException("Custom exception occurred");
        }

        // Kode transaksi lebih lanjut
    }
}

```

# Propagation

propagation adalah elemen opsional dalam anotasi @Transactional di Spring Framework yang menentukan bagaimana transaksi harus disebarkan atau dikelola ketika metode beranotasi dipanggil. Ini mengatur bagaimana transaksi baru harus dimulai atau transaksi yang sudah ada harus digunakan atau diubah.

propagation mengatur perilaku transaksi saat metode yang beranotasi dipanggil dalam konteks transaksi yang sudah ada atau tidak ada. Ini menentukan apakah harus memulai transaksi baru, menggunakan transaksi yang ada, atau berbagai perilaku lainnya.

Tujuannya adalah untuk mengontrol manajemen transaksi dengan cara yang fleksibel, memungkinkan integrasi yang tepat antara berbagai bagian aplikasi dan penanganan skenario transaksi yang kompleks.
 
### Jenis-jenis Propagation:

- REQUIRED: Propagation default. Jika ada transaksi aktif, metode akan dijalankan dalam transaksi tersebut. Jika tidak ada transaksi, akan memulai transaksi baru.
- REQUIRES_NEW: Selalu memulai transaksi baru. Jika ada transaksi aktif, transaksi tersebut akan ditangguhkan selama metode ini berjalan.
- NESTED: Memulai transaksi baru dalam transaksi utama yang ada. Transaksi baru ini adalah "nested" di dalam transaksi utama. Jika transaksi nested di-rollback, hanya nested transaction yang akan di-rollback, sedangkan transaksi utama tetap berjalan.
- MkitaTORY: Mendukung transaksi saat ini. Jika tidak ada transaksi yang ada, akan melempar exception.
- NEVER: Tidak boleh menjalankan transaksi. Jika ada transaksi yang ada, akan melempar exception.
- NOT_SUPPORTED: Tidak mendukung transaksi saat ini. Jika ada transaksi yang ada, transaksi tersebut akan ditangguhkan selama metode ini berjalan.
- SUPPORTS: Mendukung transaksi saat ini jika ada, tetapi tidak memulai transaksi baru. Metode ini bisa berjalan di dalam atau di luar konteks transaksi.

### Contoh Penggunaan
``` 
@Service
public class MyService {

    // REQUIRED || REQUIRES_NEW || NESTED || MkitaTORY || NEVER || NOT_SUPPORTED || SUPPORTS

    @Transactional(propagation = Propagation.REQUIRED)
    public void methodRequired() {
        // Kode transaksi
    }
}
```
# readOnly

readOnly adalah elemen opsional dalam anotasi @Transactional di Spring Framework yang digunakan untuk menunjukkan apakah transaksi yang akan dijalankan bersifat read-only (hanya baca) atau tidak. Ketika readOnly disetel ke true, ini memberikan sinyal kepada manajer transaksi bahwa tidak ada operasi penulisan (seperti insert, update, atau delete) yang akan dilakukan dalam transaksi tersebut. Hal ini dapat memungkinkan beberapa optimasi di tingkat database.

readOnly adalah sebuah flag boolean yang dapat diatur ke true jika transaksi tersebut hanya akan melakukan operasi baca saja.

Menkitai transaksi sebagai read-only dapat membantu dalam mengoptimalkan performa transaksi karena database dapat menghindari beberapa mekanisme penguncian dan logging yang diperlukan untuk operasi tulis.

readOnly biasanya digunakan pada metode yang hanya melakukan operasi baca pada database, seperti mengambil data tanpa mengubahnya.

### Contoh Penggunaan

``` 
@Service
public class MyService {

    @Transactional(readOnly = true)
    public List<Product> getAllProducts() {
        // Kode untuk mendapatkan semua produk
        return productRepository.findAll();
    }
}

```

# rollbackFor

rollbackFor adalah elemen opsional dalam anotasi @Transactional di Spring Framework yang menentukan jenis-jenis exception yang harus menyebabkan rollback transaksi. Dengan menggunakan rollbackFor, kita dapat mengatur exception mana yang harus memicu rollback meskipun secara default mungkin tidak.

rollbackFor mendefinisikan satu atau lebih jenis exception yang, ketika dilemparkan, akan menyebabkan rollback transaksi. Exception ini harus merupakan subclass dari Throwable.

Tujuan dari rollbackFor adalah untuk memberikan kontrol lebih besar atas rollback transaksi, memungkinkan developer untuk menentukan exception spesifik yang harus menyebabkan rollback.

rollbackFor biasanya digunakan ketika kita ingin memastikan bahwa transaksi di-rollback untuk jenis exception tertentu yang mungkin tidak menyebabkan rollback secara default (misalnya, checked exceptions).

### Contoh Penggunaan

``` 
@Service
public class MyService {

    @Transactional(rollbackFor = CustomException.class)
    public void performTransaction() {
        // Kode transaksi

        // Exception yang menyebabkan rollback
        if (someCondition) {
            throw new CustomException("Custom exception occurred");
        }

        // Kode transaksi lebih lanjut
    }
}
```

# rollbackForClassName

rollbackForClassName adalah elemen opsional dalam anotasi @Transactional di Spring Framework yang menentukan pola nama exception yang harus menyebabkan rollback transaksi. Ini mirip dengan rollbackFor, tetapi menggunakan nama class exception dalam bentuk string, memungkinkan kita untuk menentukan exception berdasarkan nama mereka.

rollbackForClassName mendefinisikan satu atau lebih nama class exception (dalam bentuk string) yang harus menyebabkan rollback transaksi ketika exception tersebut dilemparkan. Nama exception harus merupakan subclass dari Throwable.

Tujuan dari rollbackForClassName adalah untuk memberikan kontrol lebih besar atas rollback transaksi, memungkinkan developer untuk menentukan exception yang mungkin tidak ada dalam classpath saat waktu kompilasi atau jika kita ingin menghindari ketergantungan langsung pada class exception tertentu.

rollbackForClassName biasanya digunakan ketika kita ingin menggunakan nama class exception sebagai string, yang bisa berguna jika kita ingin menentukan exception yang mungkin tidak ada dalam classpath saat waktu kompilasi atau jika kita ingin menghindari ketergantungan langsung pada class exception tertentu.

### Contoh Penggunaan

``` 
@Service
public class MyService {

    @Transactional(rollbackForClassName = "com.example.CustomException")
    public void performTransaction() {
        // Kode transaksi

        // Exception yang menyebabkan rollback
        if (someCondition) {
            throw new CustomException("Custom exception occurred");
        }

        // Kode transaksi lebih lanjut
    }
}
```

# timeout

timeout adalah elemen opsional dalam anotasi @Transactional di Spring Framework yang menentukan batas waktu (dalam detik) untuk sebuah transaksi. Jika transaksi tidak selesai dalam jangka waktu yang ditentukan, transaksi tersebut akan di-rollback secara otomatis oleh manajer transaksi.

timeout menentukan batas waktu maksimal dalam detik untuk sebuah transaksi sebelum dianggap gagal dan di-rollback. Jika transaksi melampaui batas waktu yang ditentukan, manajer transaksi akan menghentikan transaksi dan membatalkan semua operasi yang dilakukan dalam transaksi tersebut.

Tujuannya adalah untuk mencegah transaksi yang berjalan terlalu lama dan menghabiskan sumber daya sistem, yang dapat menyebabkan kinerja buruk dan kemungkinan deadlock.

timeout digunakan untuk memastikan bahwa transaksi yang memakan waktu terlalu lama akan dihentikan dan di-rollback untuk menjaga kinerja dan konsistensi sistem.

### Contoh Penggunaan

``` 
@Service
public class MyService {

    @Transactional(timeout = 30) // 30 detik
    public void performTransaction() {
        // Kode transaksi yang harus diselesaikan dalam 30 detik
    }
}
```

# timeoutString
timeoutString adalah elemen opsional dalam anotasi @Transactional di Spring Framework yang memungkinkan kita untuk menentukan batas waktu transaksi sebagai string. Ini berguna jika kita ingin mengatur nilai timeout secara dinamis melalui konfigurasi atau variabel lingkungan.

timeoutString adalah sebuah string yang menentukan batas waktu maksimal dalam detik untuk sebuah transaksi sebelum dianggap gagal dan di-rollback. Nilai ini biasanya diambil dari konfigurasi atau variabel lingkungan.

Tujuan dari timeoutString adalah memberikan fleksibilitas dalam mengatur batas waktu transaksi tanpa harus mengubah kode sumber. Ini memungkinkan penyesuaian waktu transaksi berdasarkan kondisi runtime atau konfigurasi yang mudah diubah.

timeoutString digunakan ketika kita ingin mengatur batas waktu transaksi secara dinamis, biasanya melalui file konfigurasi seperti application.properties atau application.yml, atau melalui variabel lingkungan.

### Contoh Penggunaan

``` 
@Service
public class MyService {

    @Transactional(timeoutString = "${transaction.timeout:30}") // Mengambil dari konfigurasi atau default 30 detik
    public void performTransaction() {
        // Kode transaksi yang harus diselesaikan dalam waktu yang ditentukan
    }
}
```

# transactionManager

transactionManager adalah elemen opsional dalam anotasi @Transactional di Spring Framework yang digunakan untuk menentukan nama TransactionManager yang akan digunakan oleh transaksi tersebut. Ini berguna ketika kita memiliki lebih dari satu TransactionManager yang dikonfigurasi dalam aplikasi kita dan kita perlu menentukan yang mana yang akan digunakan untuk transaksi tertentu.

transactionManager menentukan nama TransactionManager yang harus digunakan untuk transaksi ini. Nama ini harus sesuai dengan nama bean dari TransactionManager yang dikonfigurasi dalam konteks Spring.

Tujuan dari transactionManager adalah untuk memberikan fleksibilitas dalam memilih TransactionManager yang sesuai saat mengelola transaksi, terutama dalam aplikasi yang menggunakan lebih dari satu sumber data atau jenis transaksi yang berbeda.

transactionManager digunakan ketika kita perlu menentukan TransactionManager tertentu untuk transaksi, biasanya dalam aplikasi yang memiliki konfigurasi multi-sumber data atau jenis transaksi yang berbeda (misalnya, transaksi JPA dan transaksi JMS).

### Contoh Penggunaan

``` 
@Configuration
public class DataSourceConfig {

    @Bean(name = "transactionManager1")
    public PlatformTransactionManager transactionManager1(DataSource dataSource1) {
        return new DataSourceTransactionManager(dataSource1);
    }

    @Bean(name = "transactionManager2")
    public PlatformTransactionManager transactionManager2(DataSource dataSource2) {
        return new DataSourceTransactionManager(dataSource2);
    }
}
```

# value

value adalah sebuah alias untuk elemen transactionManager dalam anotasi @Transactional di Spring Framework. Ini memungkinkan kita untuk menentukan TransactionManager yang akan digunakan untuk transaksi tanpa perlu menuliskan kata kunci transactionManager secara eksplisit.

value adalah sebuah string yang menentukan nama TransactionManager yang harus digunakan untuk transaksi ini. Nama ini harus sesuai dengan nama bean dari TransactionManager yang dikonfigurasi dalam konteks Spring.

Tujuan dari value adalah untuk menyederhanakan penggunaan anotasi @Transactional dengan memberikan cara singkat untuk menentukan TransactionManager yang digunakan.

value digunakan di tempat transactionManager untuk menentukan nama TransactionManager tanpa perlu menuliskan kata kunci transactionManager secara eksplisit.

### Contoh Penggunaan

``` 
@Service
public class MyService {

    @Transactional("transactionManager1")
    public void performTransaction1() {
        // Kode transaksi menggunakan transactionManager1
    }

    @Transactional("transactionManager2")
    public void performTransaction2() {
        // Kode transaksi menggunakan transactionManager2
    }
}

```
