package simple.chatbot.dataprovider.postgres.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import simple.chatbot.entity.LangUsedEnum;
import simple.chatbot.entity.PhraseTypeEnum;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

/**
 * Phrase for response
 *
 * @date 01.12.2020
 * @author Skyhunter
 */
@Entity
@Table(name = "chatbot_phrase")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ChatbotPhrase {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "chatbot_phrase_id_seq")
    @SequenceGenerator(name = "chatbot_phrase_id_seq", sequenceName = "chatbot_phrase_phrase_id_seq", allocationSize = 1)
    @Column(name = "phrase_id")
    @NotNull
    private Integer phraseId;

    @Column(name = "phrase_type_code")
    @Enumerated(EnumType.STRING)
    @NotNull
    private PhraseTypeEnum phraseTypeCode = PhraseTypeEnum.TEXT;

    @Column(name = "phrase_content")
    @NotNull
    private String phraseContent;

    @Column(name = "phrase_create_date")
    @NotNull
    private Timestamp phraseCreateDate = new Timestamp(new Date().getTime());

    @Column(name = "phrase_lang")
    @Enumerated(EnumType.STRING)
    @NotNull
    private LangUsedEnum phraseLang = LangUsedEnum.RU;

    public ChatbotPhrase() {

    }

    public ChatbotPhrase(@NotNull PhraseTypeEnum phraseTypeCode, @NotNull String phraseContent, @NotNull Timestamp phraseCreateDate, @NotNull LangUsedEnum phraseLang) {
        this.phraseTypeCode = phraseTypeCode;
        this.phraseContent = phraseContent;
        this.phraseCreateDate = phraseCreateDate;
        this.phraseLang = phraseLang;
    }

    public Integer getPhraseId() {
        return phraseId;
    }

    public PhraseTypeEnum getPhraseTypeCode() {
        return phraseTypeCode;
    }

    public String getPhraseContent() {
        return phraseContent;
    }

    public Timestamp getPhraseCreateDate() {
        return phraseCreateDate;
    }

    public LangUsedEnum getPhraseLang() {
        return phraseLang;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChatbotPhrase that = (ChatbotPhrase) o;
        return phraseId.equals(that.phraseId);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.phraseId);
        return hash;
    }
}
