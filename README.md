# AppnomiOdev

İstenilen:

Shopiroller 'ın API sini kullanarak bir uygulama yapılmasını istiyoruz.

- Bu uygulamadanın ana sayfasında kategorilerin listelenmesi, bir kategoriye tıklanınca bu kategoriye ait ürünlerin listelenmesini ve bu listedeki bir ürüne tıklanınca ürün detayının açılmasını istiyoruz.
- Ürünlerin listelendiği ekranda sıralama fonksiyonu olmalı. Sort parametresi değer olarak şu değerleri alabilir : `Price`,`Title`,`PublishmentDate`
- Ürünlerin listelendiği ekranda ürünlerin resmi, başlığı, fiyatı, varsa indirimli fiyatı gözükmeli.
- Ürün detay ekranında ürün resmi, başlığı, fiyatı, varsa indirimli fiyatı, ürün açıklaması, stokta kalmadıysa stokta kalmadığına dair bir badge konulması ve sepete ekle butonunun eklenmesi.

Giriş:

Öncelikle projeyi yapmak için ANDROID STUDIO ortamını seçtim. Programlama dili olarak da daha önceki yaptığım projeler kadar hakim olduğum JAVA 'yı tercih ediyorum. Şunu söylemek istiyorum ki her yeni bir projeye başladığım kendime yeni şeyler kattığımı keşfediyorum. Örneğin bu projeyi yaparken daha önce kullanmadığım bir kütüphaneyi kullandım. Önceki projemde kullandığım algoritmaları daha kısa halde kullanmayı keşfettim.

*CLASS DOSYALARININ KONUMU:
Appnomi_odev/app/src/main/java/com/example/appnomiodev/

*LAYOUT DOSYALARININ KONUMU:
Appnomi_odev/app/src/main/res/layout/

Tasarım:

Açıkcası bu proje çok daha iyi bir şekilde tasarlanabilirdi. Örneğin kayan bannerlar, yanıp sönen indirim ikonları vs. Fakat 1 hafta gibi kısa bir süre zarfında oluşturduğumuz için ve günde sadece bir kaç saatimi ayırabildiğim için biraz basic bir yapıda olmak zorunda kaldı. Ayrıca ben herhangi bir projenin tasarımını yaparken aşırı fikir değiştiren birisiyim. Projeyi yapmak yerine aynı tasarım üzerinde günlerimi hatta aylarımı harcayabilirim. Bu yanımdan hem hoşlanıyor hemde nefret ediyorum.

Postman:

Postman den verileri incelediğimde ürünlerin istenilenden çok daha fazla özelliğe sahip olduğunu gördüm. Bunları kullanarak çok daha komplex yapılar yapılabilir. Ben şuanda sadece istenilen projeyi tamamlamak istiyorum. Bu yüzden ihtiyacım olan özellikleri kullanacağım.

Bağlantı hızı:

Verileri sunucudan çekerken oldukça yavaş bir veri akışı var. Bu sunucudan mı kaynaklı kullandığım volley kütüphanesinden mi kaynaklı tam anlayamadım. Açıkcası Postman de de yavaş bir veri akışı vardı. Sırf bu yüzden bütün verileri ilk başta çekip daha sonrasında uygulama içerisinde kullanmak gibi aklımda fikirler oluştu fakat bunu neden yapmadığımı birazdan açıklayacağım.

İlk oluşturma:

Yeni projeyi oluştururken minimum api seviyesini 5.0 (Lolipop) olarak tercih ettim. Bu sayede çok daha fazla cihaz desteklenebilecek. Bu api seviyesini önerilende otomatik olarak gösterildiği için tercih ettim. Genelde 4.4 Kitkat ı tercih ediyorum. Daha sonrasında programın ilk başladığı MainActivity class ı ve buna ait olan Layout dosyası oluşuyor. MainActivity dosyasının adını CategoriesActivity olarak değiştiriyorum.

Gradle:

Default olarak gelen kütüphanelerin yanısıra kendim bu projede kullanmak için Glide ve Volley kütüphanelerini ekledim. Volley 'i GET işlemleri için Glide ' ı da sunucuda bulunan resimleri çekmek için kullanıyorum. Ayriyetten BUILD FEATURES olarak View Binding i ekliyorum. Bu sayede layout dosyasına bir obje eklediğim zaman kod kısmında sürekli bunu yeniden çağırıp oluşturmam gerekmiyor.

Kategorileri getirme:

CategoriesActivity 'de ilk olarak load_categoreis() fonksiyonumu çağırıyorum.
İhtiyacım olan verileri JSONWorkbench class (buraya değineceğim) dosyamı kullanarak bir ArrayList e aktarıyorum.
Tabi yavaş bir bağlantı olduğu için verilerin gelmesini en fazla 10 saniye countdown timer yardımı ile bekliyorum. Veriler geldiği an timer ı iptal ediyorum. Eğer 10 saniye boyunca veriler gelmez ise ekranda "Lütfen internet bağlantınızı kontrol edin" içeriğinde bir Toast oluşturuyorum. Hemen ardına tekrar verileri çekmeyi deneyip timer ı yeniden başlatıyorum (Bunun yerine fonksiyon recursive hale de getirilebilir).

JSONWorkBench GET():

Bu class dosyamızda finishstatus adlı boolean tipinde değişkenimiz var. Bunu class dışından sürekli true olup olmadığının kontrolünü yaparak verilerimizin hazır olup olmadığını kontrol ediyoruz. Bunun haricinde Volley kütüphanesi ve HashMap kullanarak verileri çekmeyi deniyoruz. Çekebildiğimiz anda JSONObject leri alıp bütün keylerini arraylist e aktarıyorum.

Kategoriler geldiğinde:

Arraylist den istediğim verileri seçip bir `CategorieItem` adlı class a bu verileri aktarıyorum. Daha sonrasında bu class ı alıp bir başka ArrayList e aktarıyorum. Aktardığım ArrayList i de `CategoriesAdapter` adlı class a gönderiyorum. Mesela ürünleri getirirken bu şekilde uğraştırıcı bir yöntem kullanmadım. Onun yerine GET fonksiyonunda bütün bu işlemleri yapıp CategorieItem class dosyalarını içeren ArrayList i döndürdüm. Bu tarz yöntemler bazen aklıma sonradan geliyor.

CategoriesAdapter:

Bu class dosyası için ayrı bir layout oluşturdum. Bu layout da kategorinin adı ve resmi mevcut. Bu classımız RecylerView.Adapter ı extendliyor. İhtiyacım olan verileri adapter ın position ından çekip gerekli yerlerde kullanıyorum. `id` değişkenini çekiyorum. `title` değişkenini çekiyorum ve TextView a yazdırıyorum. Daha sonrasında `icon` değişkenini çekiyorum. Bu değişken resmin adresini içeriyor. Glide kütüphanesini kullanarak imageView a resmi aktarıyorum. Bunun haricinde tüm layout u kap









