/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import entities.Member;

/**
 *
 * @author Lasse Emil
 */
public class MemberDTO {

    private long id;
    private String name, fave;

    public MemberDTO() {
    }

//    public MemberDTO(String name, String fave) {
//        this.name = name;
//        this.fave = fave;
//    }

    public MemberDTO(Member member) {
        this.id = member.getId();
        this.name = member.getName();
        this.fave = member.getFave();
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFave() {
        return fave;
    }

    public void setFave(String fave) {
        this.fave = fave;
    }

}
