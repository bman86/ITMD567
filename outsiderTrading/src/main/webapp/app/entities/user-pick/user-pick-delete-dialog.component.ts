import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { UserPick } from './user-pick.model';
import { UserPickPopupService } from './user-pick-popup.service';
import { UserPickService } from './user-pick.service';

@Component({
    selector: 'jhi-user-pick-delete-dialog',
    templateUrl: './user-pick-delete-dialog.component.html'
})
export class UserPickDeleteDialogComponent {

    userPick: UserPick;

    constructor(
        private userPickService: UserPickService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.userPickService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'userPickListModification',
                content: 'Deleted an userPick'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-user-pick-delete-popup',
    template: ''
})
export class UserPickDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private userPickPopupService: UserPickPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.userPickPopupService
                .open(UserPickDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
