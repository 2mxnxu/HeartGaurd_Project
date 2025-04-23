package HeartGuard.Hospital.model.entity;

public class HospitalEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int hno;
    private String hid;
    private String hpwd;
    private int apino;

    public HospitalDto toDto(){
        return HospitalDto.builder()
                .hno(hno)
                .hid(hid)
                .hpwd(hpwd)
                .apino(apino)
                .build();
    }
}
