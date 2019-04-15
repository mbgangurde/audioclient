package com.mediastore.audioclient.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/**
 * @author mbgangurde
 *
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class Audio {

	public void setAudioId(Long audioId) {
		this.audioId = audioId;
	}

	public void setSpeakerGender(Gender speakerGender) {
		this.speakerGender = speakerGender;
	}

	public void setRecordingDate(Date recordingDate) {
		this.recordingDate = recordingDate;
	}

	public void setSpeakerName(String speakerName) {
		this.speakerName = speakerName;
	}

	public void setdBFile(DBFile dBFile) {
		this.dBFile = dBFile;
	}

	private Long audioId;

	private Gender speakerGender;

	private Date recordingDate;

	private String speakerName;

	private DBFile dBFile;

	public Audio() {
		super();
	}

	public Audio(Gender speakerGender, Date recordingDate, String speakerName) {
		super();
		this.speakerGender = speakerGender;
		this.recordingDate = recordingDate;
		this.speakerName = speakerName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((audioId == null) ? 0 : audioId.hashCode());
		result = prime * result + ((recordingDate == null) ? 0 : recordingDate.hashCode());
		result = prime * result + ((speakerGender == null) ? 0 : speakerGender.hashCode());
		result = prime * result + ((speakerName == null) ? 0 : speakerName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Audio other = (Audio) obj;
		if (audioId == null) {
			if (other.audioId != null)
				return false;
		} else if (!audioId.equals(other.audioId))
			return false;
		if (recordingDate == null) {
			if (other.recordingDate != null)
				return false;
		} else if (!recordingDate.equals(other.recordingDate))
			return false;
		if (speakerGender != other.speakerGender)
			return false;
		if (speakerName == null) {
			if (other.speakerName != null)
				return false;
		} else if (!speakerName.equals(other.speakerName))
			return false;
		return true;
	}

}
