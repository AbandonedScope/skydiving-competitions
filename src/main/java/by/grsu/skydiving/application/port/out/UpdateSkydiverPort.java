package by.grsu.skydiving.application.port.out;

import by.grsu.skydiving.application.domain.model.skydiver.Skydiver;

public interface UpdateSkydiverPort {
    Skydiver updateInternal(Skydiver skydiver);

    Skydiver updateExternal(Skydiver skydiver);

    void setDeleted(long skydiverId, boolean isDeleted);
}
