package com.example.clone;

import com.example.clone.entity.News;
import com.example.clone.service.NewsService;
import com.example.clone.util.Enums;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.util.Set;

@SpringBootApplication
public class CloneApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(CloneApplication.class, args);
    }

    @Bean
    @Qualifier("restTemplate")
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
    @Autowired
    NewsService newsService;

    @Override
    public void run(String... arg0) throws Exception {
        News news = new News();
        news.setId(1);
        news.setCreatedAt(news.getCreatedAt());
        news.setTitle("Người thân, đồng đội tiễn biệt ba cảnh sát chữa cháy");
        news.setDescription("HÀ NỘIHàng trăm người thân, đồng đội có mặt tại bệnh viện, nhà riêng, " +
                "hai bên đường để tiễn biệt ba cảnh sát chữa cháy quận Cầu Giấy trong ngày tổ chức tang lễ.");
        news.setImg("");
        news.setContent("Sáng 5/8, đông đảo người thân, gia quyến, đồng đội, bạn bè đến nhà tang lễ Bệnh viện 198 (Hà Nội) làm lễ nhập quan," +
                " di quan cho 3 liệt sĩ thượng tá Đặng Anh Quân (45 tuổi), thượng úy Đỗ Đức Việt (24 tuổi), hạ sĩ Nguyễn Đình Phúc (19 tuổi)." +
                "Trước đó, ba cảnh sát hy sinh khi chữa cháy quán karaoke tại phố Quan Hoa, quận Cầu Giấy. Trước khi hy sinh," +
                " cả ba đã hướng dẫn 8 người thoát khỏi đám cháy." +
                "Di ảnh ba chiến sĩ được đặt trang trọng tại nhà tang lễ." +
                "Để tri ân các liệt sĩ, Công an TP Hà Nội quyết định tổ chức lễ tang theo nghi thức công an nhân dân."+
                "Sau lễ nhập quan lúc 8h40, thi hài 3 liệt sĩ được đưa từ bệnh viện qua nhà riêng từng người và về Nhà tang lễ quốc gia " +
                "(số 5 Trần Thánh Tông, quận Hai Bà Trưng). " +
                "Lễ viếng từ 13h đến 17h ngày 5/8. Sau đó thi hài ba liệt sĩ được đưa đi an táng tại Nghĩa trang liệt sĩ TP Hà Nội." +
                "Cùng ngày, Phó thủ tướng Phạm Bình Minh ký cấp bằng Tổ quốc ghi công với ba liệt sĩ. Bộ trưởng Công an Tô Lâm " +
                "đã ký quyết định truy thăng cấp bậc hàm từ trung tá lên thượng tá với Đội trưởng Đặng Anh Quân; từ trung úy lên " +
                "thượng úy với chiến sĩ Đỗ Đức Việt; từ binh nhì lên hạ sĩ với chiến sĩ nghĩa vụ Nguyễn Đình Phúc");
        news.setViews(9);
        news.setStatus(Enums.NewsStatus.active);
        news.setCategories(news.getCategories());
        news.setAuthor("Phạm Chiểu");

        News news1 = new News();
        news1.setId(2);
        news1.setCreatedAt(news.getCreatedAt());
        news1.setTitle("Cơn nấc cảnh báo điều gì về sức khỏe?");
        news1.setDescription("Nấc cụt có thể là dấu hiệu của tình trạng căng thẳng quá mức, bệnh trào ngược axit dạ dày, viêm phổi, đột quỵ hoặc đau tim.");
        news1.setImg("");
        news1.setContent("Bệnh trào ngược axit dạ dày: " +
                "Dấu hiệu của trào ngược dạ dày bao gồm chứng ợ nóng, có vị đắng và buồn nôn. " +
                "Tuy nhiên, nấc cụt liên tục không ngớt cũng là một triệu chứng của trào ngược dạ dày. " +
                "Theo các chuyên gia, những người bị ợ nóng kèm nấc cụt một thời gian dài có thể đến gặp bác sĩ để kiểm tra xem có bị trào ngược axit hay không. " +
                "Bệnh có thể gây ảnh hưởng đến lối sống, sức khỏe dạ dày và thực quản." +
                "Căng thẳng tâm lý: " +
                "Nấc cụt có thể là dấu hiệu cho thấy một số người cần dành nhiều thời gian chăm sóc bản thân hơn. " +
                "Mayo Clinic liệt kê căng thẳng về mặt cảm xúc là một trong nhiều nguyên nhân gây ra nấc cụt. " +
                "Các chuyên gia khuyến nghị thực hiện các biện pháp như thiền, tập thể dục hoặc nghỉ ngơi điều độ để giảm thiểu những áp lực về tinh thần." +
                "Bệnh mạn tính nghiêm trọng: "+
                "Nấc cụt có thể tồn tại ở một số người mắc các dạng bệnh nghiêm trọng hơn. " +
                "Trong một số trường hợp hiếm hoi, nấc cụt kéo dài vài ngày (thậm chí vài tuần) ở người bị bệnh ung thư, " +
                "bao gồm ung thư trực tràng, đại tràng, phổi, tuyến tụy, gan, thận cũng như bệnh bạch cầu và u lympho, " +
                "theo một nghiên cứu xuất bản năm 2018 trên tạp chí Oncology Time. " +
                "Một số bệnh nhân bị nấc cụt dai dẳng (kéo dài hơn 48 giờ), trong khi những bệnh nhân khác bị nấc cụt mạn tính (kéo dài hơn hai tháng)." +
                "Triệu chứng đột quỵ sớm: " +
                "Vấn đề về chức năng thận: ");
        news1.setViews(6);
        news1.setStatus(Enums.NewsStatus.active);
        news1.setCategories(news1.getCategories());
        news1.setAuthor("Ngọc Linh");

        News news2 = new News();
        news2.setId(3);
        news2.setCreatedAt(news.getCreatedAt());
        news2.setTitle("Trúng độc đắc 205 tỷ đồng khi mua Vietlott theo sinh nhật vợ");
        news2.setDescription("Chủ nhân giải thưởng Vietlott hơn 205 tỷ đồng cho biết dãy số trúng thưởng của tấm vé may mắn được ông mua dựa trên ngày sinh nhật của vợ.");
        news2.setImg("");
        news2.setContent("Sáng 22/7, Công ty Xổ số Điện toán Việt Nam (Vietlott) đã tổ chức lễ trao thưởng cho 2 tỷ phú Jackpot trúng giải trong 2 kỳ quay số liên tiếp là 748 và 749.");
        news2.setViews(8);
        news2.setStatus(Enums.NewsStatus.active);
        news2.setCategories(news2.getCategories());
        news2.setAuthor("Tổng hợp");

        News news3 = new News();
        news3.setId(4);
        news3.setCreatedAt(news.getCreatedAt());
        news3.setTitle("Maybach S-Class Sedan");
        news3.setDescription("Điểm nổi bật");
        news3.setImg("");
        news3.setContent("Diện mạo lôi cuốn. Nội thất ấn tượng: " +
                "Đẳng cấp dẫn đầu phân khúc xe siêu sang: lớp sơn hai tông màu huyền thoại cùng  chín kiểu kết hợp màu sắc ngoại thất khác nhau. " +
                "Thế giới bên trong Mercedes-Maybach S-Class khẳng định dấu ấn riêng của bạn với hàng loạt lựa chọn độc quyền cho màu da bọc nội thất, " +
                "các chi tiết ốp cùng những đường chỉ may tương phản đầy ấn tượng." +
                "Một chương mới đặc biệt trong lịch sử ngành ô tô:"+
                "Sự hoàn mỹ trong phong cách di chuyển chính là chuẩn mực của Mercedes-Maybach S-Class. " +
                "Vẻ lịch lãm, sang trọng cùng những tiện nghi bậc nhất mang lại trải nghiệm vô cùng độc đáo cho mỗi chuyến đi. "+
                "Thương hiệu – Định nghĩa mới của sự hoàn mỹ:"+
                "Maybach: nghệ thuật chế tác ô tô đỉnh cao. \n" +
                "\n" +
                "Kể từ khi Maybach-Motorenbau GmbH ra mắt chiếc xe đầu tiên vào năm 1921, " +
                "thương hiệu Maybach luôn được giới mộ điệu trên khắp thế giới biết đến với hiệu suất vận hành vượt trội và thiết kế sang trọng lịch lãm." +
                " Giờ đây, Mercedes-Benz đã kế thừa và phát huy những di sản của thương hiệu Maybach theo một hình ảnh hoàn toàn mới.\n" +
                "Vào những năm 20 và 30 của thế kỷ trước, những chiếc xe do Maybach sản xuất được biết đến là đại diện ưu tú nhất của sự tinh mỹ, " +
                "sang trọng cùng công nghệ tiên phong hàng đầu. Đến tận ngày nay, thương hiệu Maybach vẫn là khát khao của nhiều tay lái trên thế giới. " +
                "Một khát khao chỉ có thể được thỏa mãn bằng Mercedes-Maybach."+
                "Sang trọng thuần khiết: "+
                "Vẻ lịch lãm truyền thống cùng tiện nghi hiện đại bậc nhất được thể hiện ở mọi góc độ. " +
                "Khả năng vận hành vượt trội của chiếc xe gây ấn tượng mạnh ngay từ cái nhìn đầu tiên. " +
                "Dòng chữ “Maybach” ở phía đầu xe và đèn pha MULTIBEAM LED trở nên nổi bật hơn nhờ các đường nét thiết kế mạnh mẽ và thanh nhã, " +
                "kết hợp cùng những chi tiết mạ chrome tinh xảo. Không gian nội thất đón chào bạn bằng sự rộng rãi, " +
                "với lối kiến trúc được tạo nên để phục vụ như một phòng làm việc di động hay một phòng lounge độc quyền. " +
                "Dù bạn cần một chiếc bàn gấp hay một ly champagne; với Mercedes-Maybach S-Class, mọi mong muốn của bạn đều là có thể.\n" +
                "\n" +
                "Trải nghiệm sự sang trọng bậc nhất bằng tất cả các giác quan. " +
                "Chức năng ENERGIZING Comfort giúp cải thiện đáng kể thể trạng lẫn tinh thần của bạn thông qua nhữngc kích thích nhỏ lên các giác quan. " +
                "Chức năng này còn được kết nối với các tiện nghi khác trên xe như điều hoà nhiệt độ THERMOTRONIC, chức năng mát-xa và tạo hương thơm, " +
                "tổng hợp thành 6 chương trình thư giãn tùy chọn.");
        news3.setViews(9);
        news3.setStatus(Enums.NewsStatus.hide);
        news3.setCategories(news3.getCategories());
        news3.setAuthor("Mercedes");
//        newsService.save(news);
//        newsService.save(news1);
//        newsService.save(news2);
//        newsService.save(news3);
    }
}
