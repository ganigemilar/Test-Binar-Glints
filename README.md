# Test-Binar-Glints

# Menjalankan Test-Binar-Glints
  1. Untuk menjalankan web applikasi Test-Binar-Glints membutuhkan JDK versi 12 keatas.
  2. Jalankan jar "backend-glints-[version].jar" dengan mengeksekusi kode "java -jar backend-glints-[version].jar" pada command line.
  3. Selesai.

# Penggunaan Web Service
  Pertama yang harus diperhatikan bahwa service API yang dapat digunakan adalah Ver 1 dan Ver 2, saat setiap kali melakukan request harus menyertakan header "X-Api-Version" seperti contoh : "X-Api-Version : 1" untuk menggunakan API Ver 1.
  
Authentication
  1. Login "http://localhost:8080/api/auth/login", Method : POST
  2. Registration "http://localhost:8080/api/auth/signup", Method : POST
  
Products
  1. Show All Products "http://localhost:8080/api/products", Method : GET
  2. Show Product By Id "http://localhost:8080/api/products/{id-product}", Method : GET
  3. Create Product "http://localhost:8080/api/products/add", Method : POST
  4. Update Product "http://localhost:8080/api/products/{id}/update", Method : PUT
  5. Delete Product "http://localhost:8080/api/products/{id}/delete", Method : DELETE

# Uji Coba Web Service
  Ada pada dokumen "Assessment-TestBinar-Glints.pdf"
